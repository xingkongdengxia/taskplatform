package com.magicube.framework.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 处理SQL特殊字符的基本方法
 *
 * @author justincai
 */
public class FilterSQLUtil {

    private static final Log log = LogFactory.getLog(FilterSQLUtil.class);

    /**
     * 过滤不安全的字符串
     *
     * @param Str
     * @return
     */
    public static String FilterSQLStr(String Str) {

//        Str = Str.replace("'", "");
        Str = Str.replace("\"", "'");
//        Str = Str.replace("&", "&amp");
//        Str = Str.replace("<", "&lt");
//        Str = Str.replace(">", "&gt");

        return Str;
    }

}
