package com.magicube.framework.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

/**
 * 日期格式化工具
 *
 * @author justincai
 */
public class DateFormatUtil {

    private static final Log log = LogFactory.getLog(DateFormatUtil.class);

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT1 = "yyyy-MM-dd";

    public static final String DURATION_FORMAT = "%s小时 %s分 %s秒 %s毫秒";

    public static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

    public static final SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);

    /**
     * 把毫秒数转为格式化日期
     *
     * @param time
     * @return
     */
    public static String formatTime(long time) {
        return sdf.format(new Date(time));
    }

    /**
     * 把毫秒数转为耗时
     *
     * @param duration
     * @return
     */
    public static String formatDuration(long duration) {
        return String.format(DURATION_FORMAT, MILLISECONDS.toHours(duration) % 24, MILLISECONDS.toMinutes(duration) % 60, MILLISECONDS.toSeconds(duration) % 60, duration % 1000);
    }

    /**
     * 把字符串转为Date
     *
     * @param strDateTime yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date getDateByStringDateTime(String strDateTime) {
        Date date = null;
        try {
            date = sdf.parse(strDateTime);
        } catch (ParseException e) {
            log.error(e);
        }
        return date;
    }

    /**
     * 把字符串转为Date
     *
     * @param strDate yyyy-MM-dd
     * @return
     */
    public static Date getDateByStringDate(String strDate) {
        Date date = null;

        if (!StringUtils.isEmpty(strDate)) {
            log.info("strDate:" + strDate);
            strDate = strDate + " 00:00:00";
            date = getDateByStringDateTime(strDate);

        } else {
            log.error("strDate can't be empty!");
        }

        return date;
    }

}
