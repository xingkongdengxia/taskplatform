package com.smg.framework.pagination;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 * 分页功能-Page对象DAO接口实现类
 *
 * @author justincai
 */
public class PageDaoImpl implements PageDao {

    private static final Log log = LogFactory.getLog(PageDaoImpl.class
    );

    private JdbcTemplate jdbcTemplate;
    private int pageSize;

    /* 
     * 获得总记录数
     */
    @Override
    public int getTotalRecord(String sql) {
        log.info("sql:" + sql);
        SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);
        int totalRecord = 0;
        if (srs.next()) {
            totalRecord = srs.getInt(1);
            log.info("totalRecord:" + totalRecord);
        } else {
            log.error("SqlRowSet is null.");
        }

        return totalRecord;
    }

    /* 
     * 获取当前页数据
     */
    @Override
    public Page getPage(int pageNum, Class clazz, String sql, int totalRecord) {
        Page page = new Page(pageNum, totalRecord, pageSize);
        sql = sql + " limit " + page.getStartIndex() + "," + page.getPageSize();
        log.info(sql);

        List list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(clazz));
        page.setList(list);
        log.info("currunt page list num:" + list.size());
        return page;
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
