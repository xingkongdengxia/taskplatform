
package com.smg.framework.common;

import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 *数据库操作工具
 * @author justincai
 */
public class DBUtil {
    
    private static final Log log = LogFactory.getLog(DBUtil.class);
    
    /*
    *从spring配置文件中得到数据源
    */
    public static DataSource getDataSource() {
        
        try {
            //在web服务器中要先加载数据库驱动，才能访问数据库
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            log.fatal("cann't load db driver", ex);
        }
        
        DriverManagerDataSource ds = (DriverManagerDataSource) SpringUtil.getBean("ds");
        
        return ds;
        
    }
    
    /*
    *从spring配置文件中得到已经配置好数据源的JdbcTemplate
    */
    public static JdbcTemplate getJdbcTemplate() {
        
        try {
            //在web服务器中要先加载数据库驱动，才能访问数据库
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            log.fatal("cann't load db driver", ex);
        }
        
        JdbcTemplate jt = (JdbcTemplate) SpringUtil.getBean("jdbcTemplate");
        
        return jt;
        
    }
}
