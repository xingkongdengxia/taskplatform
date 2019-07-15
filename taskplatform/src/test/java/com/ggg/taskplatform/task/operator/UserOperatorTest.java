package com.ggg.taskplatform.task.operator;

import com.ggg.taskplatform.task.operator.UserOperator;
import com.magicube.framework.common.SpringUtil;
import com.magicube.framework.upms.dao.model.UpmsUser;
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
        //assertEquals(expResult, result);
    }

    /**
     * Test of getUsersByUsernameSeq method, of class UserOperator.
     */
    @Test
    public void testGetUsersByUsernameSeq() {
        System.out.println("getUsersByUsernameSeq");
        /*String usernameSeq = "caihuachen,";
        UserOperator instance = (UserOperator) SpringUtil.getBean("userOperator");
        String expResult = "caihuachen";
        List<UpmsUser> result = instance.getUsersByUsernameSeq(usernameSeq);
//        UpmsUser upmsUser = result.get(0);
        //assertEquals(expResult, upmsUser.getUsername());

        usernameSeq = "chenzijian,test,caihuachen,";
        result = instance.getUsersByUsernameSeq(usernameSeq);
        upmsUser = result.get(0);
        expResult = "chenzijian";
        //assertEquals(expResult, upmsUser.getUsername());

        upmsUser = result.get(1);
        expResult = "test";
        //assertEquals(expResult, upmsUser.getUsername());

        upmsUser = result.get(2);
        expResult = "caihuachen";
        //assertEquals(expResult, upmsUser.getUsername());
        //assertTrue(result.size() == 3);
         */
    }

    /**
     * Test of getRealNameSeqByUserList method, of class UserOperator.
     */
    @Test
    public void testGetRealNameSeqByUserList() {
        System.out.println("getRealNameSeqByUserList");
        UserOperator instance = (UserOperator) SpringUtil.getBean("userOperator");

        String usernameSeq = "chenzijian,test,caihuachen,";
        List<UpmsUser> userList = instance.getUsersByUsernameSeq(usernameSeq);

        String expResult = "陈子建,蔡华臣test,蔡华臣";
        String realNameSeq = instance.getRealNameSeqByUserList(userList);
//        assertEquals(expResult, realNameSeq);
        System.out.println("realNameSeq:" + realNameSeq);
    }

    /**
     * Test of getRealNameSeqByUsernameSeq method, of class UserOperator.
     */
    @Test
    public void testGetRealNameSeqByUsernameSeq() {
        System.out.println("getRealNameSeqByUsernameSeq");
        UserOperator instance = (UserOperator) SpringUtil.getBean("userOperator");

        String usernameSeq = "chenzijian,test,caihuachen,";
        String expResult = "陈子建,蔡华臣test,蔡华臣";
        String result = instance.getRealNameSeqByUsernameSeq(usernameSeq);
//        assertEquals(expResult, result);
        System.out.println("result:" + result);
    }

    /**
     * Test of getAllUsersStr method, of class UserOperator.
     */
    @Test
    public void testGetAllUsersStr() {
        System.out.println("getAllUsersStr");
        UserOperator instance = (UserOperator) SpringUtil.getBean("userOperator");

        String result = instance.getAllUsersStr();
        System.out.println(result);
    }

}
