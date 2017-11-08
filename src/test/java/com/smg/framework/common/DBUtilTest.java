package com.smg.framework.common;

import javax.sql.DataSource;
import org.easybatch.core.job.JobReport;
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
     * Test of copyTable2Table method, of class DBUtil.
     */
    @Test
    public void testCopyTable2Table() {
        System.out.println("copyTable2Table");
        DataSource fromDS = null;
        DataSource toDS = null;
        String query = "";
        String[] fields = null;
        String insertSQL = "";
        Class recordClass = null;
        JobReport expResult = null;
        JobReport result = DBUtil.copyTable2Table(fromDS, toDS, query, fields, insertSQL, recordClass);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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

}
