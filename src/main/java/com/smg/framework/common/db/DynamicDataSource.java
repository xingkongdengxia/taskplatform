package com.smg.framework.common.db;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源（数据源切换）
 *
 * @author justincai
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final Log log = LogFactory.getLog(DynamicDataSource.class);

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSource = getDataSource();
        log.info("当前操作使用的数据源："+ dataSource);
        return dataSource;
    }

    /**
     * 设置数据源
     *
     * @param dataSource
     */
    public static void setDataSource(String dataSource) {
        contextHolder.set(dataSource);
    }

    /**
     * 获取数据源
     *
     * @return
     */
    public static String getDataSource() {
        String dataSource = contextHolder.get();
        // 如果没有指定数据源，使用默认数据源
        if (null == dataSource) {
            DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getDefault());
        }
        return contextHolder.get();
    }

    /**
     * 清除数据源
     */
    public static void clearDataSource() {
        contextHolder.remove();
    }

}
