
package com.smg.framework.examples.pagination;

import com.smg.framework.common.SpringUtil;
import com.smg.framework.pagination.Page;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author justincai
 */
public class PageServiceTest {

    public PageServiceTest() {
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
     * Test of getActionSettingPage method, of class PageService.
     */
    @Test
    public void testGetActionSettingPage() {
        System.out.println("getActionSettingPage");

        PageService ps = (PageService) SpringUtil.getBean("pageService");
        int pageNum = 1;
        Page page = ps.getActionSettingPage(pageNum);
        List list = page.getList();
        List expResult = null;
        assertNotNull(list);
        
    }

}
