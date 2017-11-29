package com.smg.framework.common;

import com.smg.framework.common.utils.DateFormatUtil;
import java.sql.Timestamp;
import java.util.Arrays;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easybatch.core.job.Job;
import static org.easybatch.core.job.JobBuilder.aNewJob;
import org.easybatch.core.job.JobExecutor;
import org.easybatch.core.job.JobReport;
import org.easybatch.jdbc.BeanPropertiesPreparedStatementProvider;
import org.easybatch.jdbc.JdbcRecordMapper;
import org.easybatch.jdbc.JdbcRecordReader;
import org.easybatch.jdbc.JdbcRecordWriter;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 数据库操作工具
 *
 * @author justincai
 */
public class DBUtil {

    private static final Log log = LogFactory.getLog(DBUtil.class);

    /**
     * 从spring配置文件中得到数据源
     *
     * @return
     *
     */
    public static DataSource getDataSource() {

        try {
            //在web服务器中要先加载数据库驱动，才能访问数据库
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            log.fatal("cann't load db driver", ex);
        }

        DataSource ds = (DataSource) SpringUtil.getBean("dataSource");

        return ds;

    }

    /**
     * 从spring配置文件中得到已经配置好数据源的JdbcTemplate
     *
     * @return
     *
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

    /**
     * 数据库表之间的拷贝
     *
     * @param fromDS 源数据库
     * @param toDS 目的数据库
     * @param query 源数据库表的查询语句，例：select deptid, setid, descr, smg_oa_deptid from
     * sysadm.PS_SMG_DEPT_SY_VW
     * @param fields model类字段名，例如："deptid", "setid", "descr", "smgOaDeptid"
     * @param insertSQL 插入目的数据库表的INSERT语句，例如：INSERT INTO
     * HR_SMG_DEPT_SY_VW(deptid, setid, descr, smg_oa_deptid) VALUES (?,?,?);
     * @param recordClass model类class 注意： 1、query中的字段顺序必须与fields中的字段顺序保持一致
     * 2、fields中的字段名字与recordClass中的字段名字保持一致（有时候model类中的字段名与数据库表的字段名不一致，比如：fields中的smgOaDeptid和insertSQL中的smg_oa_deptid）
     * @return
     *
     */
    public static JobReport copyTable2Table(DataSource fromDS, DataSource toDS,
            String query, String[] fields, String insertSQL, Class recordClass) {

        log.info("query:" + query);
        log.info("fields:" + Arrays.toString(fields));
        log.info("insertSQL:" + insertSQL);
        log.info("recordClass:" + recordClass.getName());

        Job job = aNewJob()
                .reader(new JdbcRecordReader(fromDS, query))
                .mapper(new JdbcRecordMapper<>(recordClass, fields))
                .writer(new JdbcRecordWriter(toDS, insertSQL, new BeanPropertiesPreparedStatementProvider(recordClass, fields)))
                .build();

        //Execute the job
        JobExecutor jobExecutor = new JobExecutor();
        JobReport jobReport = jobExecutor.execute(job);
        jobExecutor.shutdown();

        return jobReport;

    }

    /**
     * 确定数据表是否存在
     *
     * @param database 数据库名
     * @param tablename 表名
     * @return
     */
    public static boolean isTableExist(String database, String tablename) {

        String sql = "SELECT count(*) FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + database + "' AND table_name = '" + tablename + "';";
        log.debug("sql:" + sql);

        JdbcTemplate jt = getJdbcTemplate();
        Integer rowCount = jt.queryForObject(sql, null, Integer.class);

        log.debug("rowCount:" + rowCount);

        return rowCount > 0;

    }

    /**
     * 确定数据表的生成时间
     *
     * @param database 数据库名
     * @param tablename 表名
     * @return
     */
    public static String getTableCreateTime(String database, String tablename) {

        String sql = "SELECT create_time FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + database + "' AND table_name = '" + tablename + "';";
        log.debug("sql:" + sql);

        JdbcTemplate jt = getJdbcTemplate();
        Timestamp ts = jt.queryForObject(sql, null, Timestamp.class);
        log.debug("ts:" + ts);

        String ctime = DateFormatUtil.formatTime(ts.getTime());

        return ctime;

    }
}
