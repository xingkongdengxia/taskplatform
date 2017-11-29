package com.smg.framework.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 日期格式化工具
 *
 * @author justincai
 */
public class DateFormatUtil {

    private static final Log log = LogFactory.getLog(DateFormatUtil.class);

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DURATION_FORMAT = "%s小时 %s分 %s秒 %s毫秒";

    public static String formatTime(long time) {
        return new SimpleDateFormat(DATE_FORMAT).format(new Date(time));
    }

    public static String formatDuration(long duration) {
        return String.format(DURATION_FORMAT, MILLISECONDS.toHours(duration) % 24, MILLISECONDS.toMinutes(duration) % 60, MILLISECONDS.toSeconds(duration) % 60, duration % 1000);
    }

}
