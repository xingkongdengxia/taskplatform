package com.ggg.taskplatform.task.operator;

import com.magicube.framework.upms.dao.model.UpmsUser;
import com.magicube.framework.upms.dao.model.UpmsUserExample;
import com.magicube.framework.upms.rpc.api.UpmsUserService;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

/**
 * 系统用户的相关操作
 *
 * @author justincai
 */
public class UserOperator {

    private static final Log log = LogFactory.getLog(UserOperator.class);

    @Autowired
    private UpmsUserService upmsUserService;

    public void setUpmsUserService(UpmsUserService upmsUserService) {
        this.upmsUserService = upmsUserService;
    }

    /**
     * 列出所有用户，除了admin
     *
     * @return
     */
    public List<UpmsUser> getAllUsersExceptAdmin() {

        UpmsUserExample upmsUserExample = new UpmsUserExample();
        upmsUserExample.or()
                .andUsernameNotEqualTo("admin");            //排除admin
        upmsUserExample.setOrderByClause("realname asc");   //按照真实姓名顺序排序
        List<UpmsUser> userList = upmsUserService.selectByExample(upmsUserExample);
        if (!ObjectUtils.isEmpty(userList)) {
            log.info("userList size:" + userList.size());
            return userList;
        } else {
            log.info("userList is null!");
            return null;
        }
    }

    /**
     * 根据用户名得到真实姓名
     *
     * @param username
     * @return
     */
    public String getRealnameByUsername(String username) {

        UpmsUserExample upmsUserExample = new UpmsUserExample();
        upmsUserExample.or()
                .andUsernameEqualTo(username);
        UpmsUser upmsUser = upmsUserService.selectFirstByExample(upmsUserExample);
        if (!ObjectUtils.isEmpty(upmsUser)) {
            log.info("the usename(" + upmsUser.getUsername() + ")'s realname is " + upmsUser.getRealname());
            return upmsUser.getRealname();
        } else {
            log.info("can't find the user by the username(" + upmsUser.getUsername() + ")");
            return null;
        }

    }

    /**
     * 根据用户名序列，得到用户列表
     *
     * @param usernameSeq
     * @return
     */
    public List<UpmsUser> getUsersByUsernameSeq(String usernameSeq) {
        List<UpmsUser> upmsUserList;
        String[] usernameList;
        if (StringUtils.isNotBlank(usernameSeq)) {
            log.info("usernameSeq:" + usernameSeq);
            usernameList = StringUtils.splitByWholeSeparator(usernameSeq, ",");
            if (!ObjectUtils.isEmpty(usernameList)) {
                log.info("usernameList size :" + usernameList.length);
                upmsUserList = new ArrayList<>();
                UpmsUserExample upmsUserExample = new UpmsUserExample();
                for (String username : usernameList) {
                    upmsUserExample.clear();
                    upmsUserExample.or().andUsernameEqualTo(username);
                    UpmsUser upmsUser = upmsUserService.selectFirstByExample(upmsUserExample);
                    if (!ObjectUtils.isEmpty(upmsUser)) {
                        log.info("username:" + upmsUser.getUsername());
                        upmsUserList.add(upmsUser);
                    }
                }
                return upmsUserList;
            } else {
                log.error("can't split the usernameSeq!");
                return null;
            }
        } else {
            log.error("usernameSeq can't be null!");
            return null;
        }
    }

    /**
     * 根据用户列表，得到用户真实姓名序列
     *
     * @param upmsUserList
     * @return
     */
    public String getRealNameSeqByUserList(List<UpmsUser> upmsUserList) {
        StringBuilder realNameSeq = new StringBuilder();
        if (!ObjectUtils.isEmpty(upmsUserList)) {
            log.info("upmsUserList size:" + upmsUserList.size());
            for (int i = 0; i < upmsUserList.size(); i++) {
                UpmsUser upmsUser = upmsUserList.get(i);
                realNameSeq.append(upmsUser.getRealname());
                if (i < upmsUserList.size() - 1) {      //除了最后一个真实姓名，前面的真实姓名都要加上逗号
                    realNameSeq.append(",");
                }
            }
        }
        return realNameSeq.toString();
    }

    /**
     * 根据用户名序列，得到用户真实姓名序列
     *
     * @param usernameSeq
     * @return
     */
    public String getRealNameSeqByUsernameSeq(String usernameSeq) {
        List<UpmsUser> upmsUserList = getUsersByUsernameSeq(usernameSeq);
        return getRealNameSeqByUserList(upmsUserList);
    }

    /**
     * 得到所有用户名字符串
     *
     * @return
     */
    public String getAllUsersStr() {
        StringBuilder allUserStr = new StringBuilder();
        List<UpmsUser> allUserList = getAllUsersExceptAdmin();
        if (!ObjectUtils.isEmpty(allUserList)) {
            log.info("allUserList size:" + allUserList.size());
            for (UpmsUser upmsUser : allUserList) {
                allUserStr.append(upmsUser.getUsername());
                allUserStr.append(",");
            }
        }
        return allUserStr.toString();
    }

}
