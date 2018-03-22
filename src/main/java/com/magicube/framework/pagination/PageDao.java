package com.magicube.framework.pagination;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 分页功能-Page对象DAO接口
 *
 * @author justincai
 */
public interface PageDao {

    /* 
     * 获得总记录数
     */
    public int getTotalRecord(String sql);

    /* 
     * 获取当前页数据
     */
    public Page getPage(int pageNum, Class clazz, String sql, int totalRecord);

    public JdbcTemplate getJdbcTemplate();

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate);

    public int getPageSize();

    public void setPageSize(int pageSize);

}
