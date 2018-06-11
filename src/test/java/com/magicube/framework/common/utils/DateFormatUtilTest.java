package com.magicube.framework.common.utils;

import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author justincai
 */
public class DateFormatUtilTest {
    
    public DateFormatUtilTest() {
    }

    /**
     * Test of formatTime method, of class DateFormatUtil.
     */
    @Test
    public void testFormatTime() {
        System.out.println("formatTime");
        
    }

    /**
     * Test of formatDuration method, of class DateFormatUtil.
     */
    @Test
    public void testFormatDuration() {
        System.out.println("formatDuration");
        
    }

    /**
     * Test of getDateByStringDateTime method, of class DateFormatUtil.
     */
    @Test
    public void testGetDateByStringDateTime() {
        System.out.println("getDateByStringDateTime");
        String strDateTime = "2018-6-4 00:00:00";
        Date result = DateFormatUtil.getDateByStringDateTime(strDateTime);
        assertNotNull(result);
        System.out.println(result.toString());
    }

    /**
     * Test of getDateByStringDate method, of class DateFormatUtil.
     */
    @Test
    public void testGetDateByStringDate() {
        System.out.println("getDateByStringDate");
        String strDate = "2018-6-4";
        Date result = DateFormatUtil.getDateByStringDate(strDate);
        assertNotNull(result);
        System.out.println(result.toString());
    }

    /**
     * Test of formatTime method, of class DateFormatUtil.
     */
    @Test
    public void testFormatTime_long() {
        System.out.println("formatTime");
        
    }

    /**
     * Test of formatTime method, of class DateFormatUtil.
     */
    @Test
    public void testFormatTime_long_String() {
        System.out.println("formatTime");
        long time = System.currentTimeMillis();
        String dateFormat = "yyyy-MM-dd";
        
        String result = DateFormatUtil.formatTime(time, dateFormat);
        System.out.println(result);
    }
    
}
