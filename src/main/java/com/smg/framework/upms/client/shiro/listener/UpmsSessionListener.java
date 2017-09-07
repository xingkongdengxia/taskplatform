package com.smg.framework.upms.client.shiro.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 *
 * @author justincai
 */
public class UpmsSessionListener implements SessionListener {

    private static final Log log = LogFactory.getLog(UpmsSessionListener.class);

    @Override
    public void onStart(Session session) {
        log.debug("会话创建：" + session.getId());
    }

    @Override
    public void onStop(Session session) {
        log.debug("会话停止：" + session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        log.debug("会话过期：" + session.getId());
    }

}
