package com.smg.taskplatform.task.service;

import com.magicube.framework.upms.dao.model.UpmsUser;
import com.magicube.framework.upms.dao.model.UpmsUserExample;
import com.magicube.framework.upms.rpc.api.UpmsUserService;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

/**
 * 系统用户的相关操作
 *
 * @author justincai
 */
public class UserService {

    private static final Log log = LogFactory.getLog(UserService.class);

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

}
