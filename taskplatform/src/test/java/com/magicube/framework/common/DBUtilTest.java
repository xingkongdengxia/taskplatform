package com.magicube.framework.common;

import com.magicube.framework.common.DBUtil;
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

    /**
     * Test of isTableExist method, of class DBUtil.
     */
    @Test
    public void testIsTableExist() {
        System.out.println("isTableExist");
        String database = "test";
        String tablename = "actionsetting";
        boolean expResult = true;
        boolean result = DBUtil.isTableExist(database, tablename);
        assertEquals(expResult, result);

    }

    @Test
    public void testGetTableCreateTime() {
        System.out.println("getTableCreateTime");
        String database = "test";
        String tablename = "actionsetting";

        String result = DBUtil.getTableCreateTime(database, tablename);
        System.out.println(result);

    }

    @Test
    public void testDeleteTable() {
        System.out.println("deleteTable");
        String database = "test";
        String tablename = "tweet";

        boolean result = DBUtil.deleteTable(database, tablename);
        System.out.println(result);

    }

}
