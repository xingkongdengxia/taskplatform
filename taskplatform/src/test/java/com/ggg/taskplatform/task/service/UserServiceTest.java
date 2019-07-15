package com.ggg.taskplatform.task.service;

import com.ggg.taskplatform.task.operator.UserOperator;
import com.magicube.framework.common.SpringUtil;
import com.magicube.framework.upms.dao.model.UpmsUser;
import com.magicube.framework.upms.rpc.api.UpmsUserService;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author justincai
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:applicationContext.xml"
})
@Transactional
public class UserServiceTest {

    public UserServiceTest() {
    }

    /**
     * Test of setUpmsUserService method, of class UserOperator.
     */
    @Test
    public void testSetUpmsUserService() {
        System.out.println("setUpmsUserService");

    }

    /**
     * Test of getAllUsersExceptAdmin method, of class UserOperator.
     */
    @Test
    public void testGetAllUsersExceptAdmin() {
        System.out.println("getAllUsersExceptAdmin");
        UserOperator instance = new UserOperator();
        UpmsUserService upmsUserService = (UpmsUserService) SpringUtil.getBean("upmsUserService");
        instance.setUpmsUserService(upmsUserService);

        List<UpmsUser> expResult = null;
        List<UpmsUser> result = instance.getAllUsersExceptAdmin();
        assertNotNull(result);

        for (UpmsUser upmsUser : result) {
            System.out.println(upmsUser.getRealname());
        }

    }

}
