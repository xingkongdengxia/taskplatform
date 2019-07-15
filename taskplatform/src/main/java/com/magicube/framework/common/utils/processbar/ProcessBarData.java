package com.magicube.framework.common.utils.processbar;

/**
 * 定义进度条功能
 *
 * @author justincai
 */
public interface ProcessBarData {

    /**
     * 设置进度条百分比
     *
     * @param percentnum
     */
    public void setProcessBarPercent(int percentnum);

    /**
     * 得到进度条百分比
     *
     * @return
     */
    public int getProcessBarPercent();

    /**
     * 设置当前数目
     *
     * @param currNum
     */
    public void setCurrNum(long currNum);

    /**
     * 设置总数
     *
     * @param totalNum
     */
    public void setTotalNum(long totalNum);

    /**
     * 得到总数
     *
     * @return
     */
    public long getTotalNum();

    /**
     * 得到当前数目
     *
     * @return
     */
    public long getCurrNum();

    /**
     * 根据当前数目和总数算出百分比
     *
     * @return
     */
    public int getCalculatePercent();

}
