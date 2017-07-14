
package com.smg.framework.common;

import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 *
 * @author justincai
 */
public class DBUtilTest {
    
    public DBUtilTest() {
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
     * Test of getDataSource method, of class DBUtil.
     */
    @Test
    public void testGetDataSource() {
        System.out.println("getDataSource");
        DataSource result = DBUtil.getDataSource();
        assertNotNull(result);
    }

    /**
     * Test of getJdbcTemplate method, of class DBUtil.
     */
    @Test
    public void testGetJdbcTemplate() {
        System.out.println("getJdbcTemplate");
        JdbcTemplate jt = DBUtil.getJdbcTemplate();
        SqlRowSet srs = jt.queryForRowSet("select * from test.charset_test_utf8");
        int rowCount = 0;
        while (srs.next()) {
            System.out.println(srs.getString("id") + " - " + srs.getString("char_col"));
            rowCount++;
        }
        System.out.println("Number of records : " + rowCount);
        assertNotNull(srs);
    }
    
}
