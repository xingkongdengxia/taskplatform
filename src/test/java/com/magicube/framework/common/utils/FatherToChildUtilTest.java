package com.magicube.framework.common.utils;

import com.magicube.framework.common.utils.FatherToChildUtil;
import com.magicube.framework.examples.fathertochild.UpmsUserChild;
import com.magicube.framework.upms.dao.model.UpmsUser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author justincai
 */
public class FatherToChildUtilTest {

    public FatherToChildUtilTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of fatherToChild method, of class FatherToChildUtil.
     * @throws java.lang.Exception
     */
    @Test
    public void testFatherToChild() throws Exception {
        System.out.println("fatherToChild");
        UpmsUser father = new UpmsUser();
        father.setUserId(1);
        father.setUsername("father:username");
        father.setRealname("father:realname");
        
        UpmsUserChild child = new UpmsUserChild();
        child.setAnotherProp("child:anotherProp");
        FatherToChildUtil.fatherToChild(father, child);

        System.out.println(child.toString());
        
        
      
    }

}
