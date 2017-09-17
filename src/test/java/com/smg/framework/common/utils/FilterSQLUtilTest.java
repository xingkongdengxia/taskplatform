package com.smg.framework.common.utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author root
 */
public class FilterSQLUtilTest {

    public FilterSQLUtilTest() {
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
     * Test of FilteSQLStr method, of class FilterSQLUtil.
     */
    @Test
    public void testFilterSQLStr() {
        System.out.println("FilterSQLStr");
        String Str = " <br>Rated Voltage = \"120VAC\"<br>Lead Model = \"AWG#18\"<br>Lead Length = '7 inch'<br>Lead Grade = '105 ℃'<br>";
        String result = FilterSQLUtil.FilterSQLStr(Str);
        System.out.println("Str:" + Str);
        System.out.println("result:" + result);
    }

}
