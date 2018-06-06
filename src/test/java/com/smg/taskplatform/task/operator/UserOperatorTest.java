package com.smg.taskplatform.task.operator;

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
public class UserOperatorTest {

    public UserOperatorTest() {
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

    }

    /**
     * Test of getRealnameByUsername method, of class UserOperator.
     */
    @Test
    public void testGetRealnameByUsername() {
        System.out.println("getRealnameByUsername");
        String username = "admin";
        UserOperator instance = (UserOperator) SpringUtil.getBean("userOperator");
        String expResult = "蔡华臣";
        String result = instance.getRealnameByUsername(username);
        assertEquals(expResult, result);
    }

}
