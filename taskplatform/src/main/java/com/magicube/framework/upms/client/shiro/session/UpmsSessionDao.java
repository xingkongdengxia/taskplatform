package com.magicube.framework.upms.client.shiro.session;

import com.magicube.framework.common.constant.UpmsConstant;
import com.magicube.framework.common.utils.RedisUtil;
import com.magicube.framework.upms.client.util.SerializableUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import redis.clients.jedis.Jedis;

/**
 * 基于redis的sessionDao，缓存共享session
 *
 * @author justincai
 */
public class UpmsSessionDao extends CachingSessionDAO {

    private static final Log log = LogFactory.getLog(UpmsSessionDao.class);

    // 会话key
    private final static String UPMS_SHIRO_SESSION_ID = "upms-shiro-session-id";
    // 全局会话key
    private final static String UPMS_SERVER_SESSION_ID = "upms-server-session-id";
    // 全局会话列表key
    private final static String UPMS_SERVER_SESSION_IDS = "upms-server-session-ids";
    // code key
    private final static String UPMS_SERVER_CODE = "upms-server-code";
    // 局部会话key
    private final static String UPMS_CLIENT_SESSION_ID = "upms-client-session-id";
    // 单点同一个code所有局部会话key
    private final static String UPMS_CLIENT_SESSION_IDS = "upms-client-session-ids";

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        RedisUtil.set(UPMS_SHIRO_SESSION_ID + "_" + sessionId, SerializableUtil.serialize(session), (int) session.getTimeout() / 1000);
        log.debug("doCreate >>>>> sessionId={" + sessionId + "}");
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        String session = RedisUtil.get(UPMS_SHIRO_SESSION_ID + "_" + sessionId);
        log.debug("doReadSession >>>>> sessionId=" + sessionId);
        return SerializableUtil.deserialize(session);
    }

    @Override
    protected void doUpdate(Session session) {
        // 如果会话过期/停止 没必要再更新了
        if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
            return;
        }
        // 更新session的最后一次访问时间
        UpmsSession upmsSession = (UpmsSession) session;
        UpmsSession cacheUpmsSession = (UpmsSession) doReadSession(session.getId());
        if (null != cacheUpmsSession) {
            upmsSession.setStatus(cacheUpmsSession.getStatus());
            upmsSession.setAttribute("FORCE_LOGOUT", cacheUpmsSession.getAttribute("FORCE_LOGOUT"));
        }
        RedisUtil.set(UPMS_SHIRO_SESSION_ID + "_" + session.getId(), SerializableUtil.serialize(session), (int) session.getTimeout() / 1000);
        // 更新ZHENG_UPMS_SERVER_SESSION_ID、ZHENG_UPMS_SERVER_CODE过期时间 TODO
        log.debug("doUpdate >>>>> sessionId={" + session.getId() + "}");
    }

    @Override
    protected void doDelete(Session session) {
        String sessionId = session.getId().toString();
        String upmsType = Objects.toString(session.getAttribute(UpmsConstant.UPMS_TYPE));
        if ("client".equals(upmsType)) {
            // 删除局部会话和同一code注册的局部会话
            String code = RedisUtil.get(UPMS_CLIENT_SESSION_ID + "_" + sessionId);
            try (Jedis jedis = RedisUtil.getJedis()) {
                jedis.del(UPMS_CLIENT_SESSION_ID + "_" + sessionId);
                jedis.srem(UPMS_CLIENT_SESSION_IDS + "_" + code, sessionId);
            }
        }
        if ("server".equals(upmsType)) {
            // 当前全局会话code
            String code = RedisUtil.get(UPMS_SERVER_SESSION_ID + "_" + sessionId);
            // 清除全局会话
            RedisUtil.remove(UPMS_SERVER_SESSION_ID + "_" + sessionId);
            // 清除code校验值
            RedisUtil.remove(UPMS_SERVER_CODE + "_" + code);
            try ( // 清除所有局部会话
                    Jedis jedis = RedisUtil.getJedis()) {
                Set<String> clientSessionIds = jedis.smembers(UPMS_CLIENT_SESSION_IDS + "_" + code);
                for (String clientSessionId : clientSessionIds) {
                    jedis.del(UPMS_CLIENT_SESSION_ID + "_" + clientSessionId);
                    jedis.srem(UPMS_CLIENT_SESSION_IDS + "_" + code, clientSessionId);
                }
                log.debug("当前code=" + code + "，对应的注册系统个数：" + jedis.scard(UPMS_CLIENT_SESSION_IDS + "_" + code) + "个");
            }
            // 维护会话id列表，提供会话分页管理
            RedisUtil.lrem(UPMS_SERVER_SESSION_IDS, 1, sessionId);
        }
        // 删除session
        RedisUtil.remove(UPMS_SHIRO_SESSION_ID + "_" + sessionId);
        log.debug("doUpdate >>>>> sessionId=" + sessionId);
    }

    /**
     * 获取会话列表
     *
     * @param offset
     * @param limit
     * @return
     */
    public Map getActiveSessions(int offset, int limit) {
        Map sessions = new HashMap();
        long total;
        List<Session> rows;
        // 获取在线会话总数
        try (Jedis jedis = RedisUtil.getJedis()) {
            // 获取在线会话总数
            total = jedis.llen(UPMS_SERVER_SESSION_IDS);
            // 获取当前页会话详情
            List<String> ids = jedis.lrange(UPMS_SERVER_SESSION_IDS, offset, (offset + limit - 1));
            rows = new ArrayList<>();
            for (String id : ids) {
                String session = RedisUtil.get(UPMS_SHIRO_SESSION_ID + "_" + id);
                // 过滤redis过期session
                if (null == session) {
                    RedisUtil.lrem(UPMS_SERVER_SESSION_IDS, 1, id);
                    total = total - 1;
                    continue;
                }
                rows.add(SerializableUtil.deserialize(session));
            }
        }
        sessions.put("total", total);
        sessions.put("rows", rows);
        return sessions;
    }

    /**
     * 强制退出
     *
     * @param ids
     * @return
     */
    public int forceout(String ids) {
        String[] sessionIds = ids.split(",");
        for (String sessionId : sessionIds) {
            // 会话增加强制退出属性标识，当此会话访问系统时，判断有该标识，则退出登录
            String session = RedisUtil.get(UPMS_SHIRO_SESSION_ID + "_" + sessionId);
            UpmsSession upmsSession = (UpmsSession) SerializableUtil.deserialize(session);
            upmsSession.setStatus(UpmsSession.OnlineStatus.force_logout);
            upmsSession.setAttribute("FORCE_LOGOUT", "FORCE_LOGOUT");
            RedisUtil.set(UPMS_SHIRO_SESSION_ID + "_" + sessionId, SerializableUtil.serialize(upmsSession), (int) upmsSession.getTimeout() / 1000);
        }
        return sessionIds.length;
    }

    /**
     * 更改在线状态
     *
     * @param sessionId
     * @param onlineStatus
     */
    public void updateStatus(Serializable sessionId, UpmsSession.OnlineStatus onlineStatus) {
        UpmsSession session = (UpmsSession) doReadSession(sessionId);
        if (null == session) {
            return;
        }
        session.setStatus(onlineStatus);
        RedisUtil.set(UPMS_SHIRO_SESSION_ID + "_" + session.getId(), SerializableUtil.serialize(session), (int) session.getTimeout() / 1000);
    }

}
