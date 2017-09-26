/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smg.framework.common.utils;

import com.smg.framework.examples.pagination.ActionSetting;
import com.smg.framework.examples.pagination.ActionSettingChild;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author root
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
        ActionSetting father = new ActionSetting();
        father.setId(1);
        father.setActionname("father:actionname");
        father.setActionclass("father:actionclass");
        father.setActionshowname("father:actionshowname");
        ActionSettingChild child = new ActionSettingChild();
        child.setAnotherProp("child:anotherProp");
        FatherToChildUtil.fatherToChild(father, child);

        System.out.println(child.toString());
    }

}
