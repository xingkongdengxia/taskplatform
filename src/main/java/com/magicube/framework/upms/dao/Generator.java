package com.magicube.framework.upms.dao;

import com.magicube.framework.common.utils.MybatisGeneratorUtil;
import com.magicube.framework.common.utils.PropertiesFileUtil;
import java.util.HashMap;
import java.util.Map;

/**
 * 代码生成类
 *
 * @author justincai
 */
public class Generator {

    // 根据命名规范，只修改此常量值即可
    private static final String MODULE = "task";
    private static final String DATABASE = "taskplatform";
    private static final String TABLE_PREFIX = "tp_task_oper_";
    private static final String PACKAGE_NAME = "com.smg.taskplatform.task";
    private static final String JDBC_DRIVER = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.driver");
    private static final String JDBC_URL = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.url");
    private static final String JDBC_USERNAME = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.username");
    private static final String JDBC_PASSWORD = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.password");
    // 需要insert后返回主键的表配置，key:表名,value:主键名
    private static final Map<String, String> LAST_INSERT_ID_TABLES = new HashMap<>();

    static {
        LAST_INSERT_ID_TABLES.put("upms_user", "user_id");
    }

    /**
     * 自动代码生成
     *
     * @param args
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        MybatisGeneratorUtil.generator(JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, MODULE, DATABASE, TABLE_PREFIX, PACKAGE_NAME, LAST_INSERT_ID_TABLES);
    }

}
