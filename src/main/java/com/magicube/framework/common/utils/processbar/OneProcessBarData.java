package com.magicube.framework.common.utils.processbar;

import java.io.Serializable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 存储进度条数据
 *
 * @author justincai
 */
public class OneProcessBarData implements ProcessBarData, Serializable {

    private static final Log log = LogFactory.getLog(OneProcessBarData.class);

    //当前数目
    private long currNum = 0;

    //总数
    private long totalNum = 0;

    //百分比 
    private int percentnum = 0;

    public OneProcessBarData() {

    }

    @Override
    public void setProcessBarPercent(int percentnum) {
        this.percentnum = percentnum;
    }

    @Override
    public int getProcessBarPercent() {
        return this.percentnum;
    }

    @Override
    public void setCurrNum(long currNum) {
        this.currNum = currNum;
    }

    @Override
    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * 根据当前数目和总数算出百分比
     *
     * @return
     */
    @Override
    public int getCalculatePercent() {

        if (totalNum == 0) {    //除数不能为0
            return 0;
        } else {
            Float result = (float) currNum / (float) totalNum * 100;
            int rtnInt = result.intValue();
            this.percentnum = rtnInt;
            log.debug("(float) currNum / (float) totalNum * 100 :" + result);
            return rtnInt;
        }

    }

    @Override
    public long getTotalNum() {
        return this.totalNum;
    }

    @Override
    public long getCurrNum() {
        return this.currNum;
    }

}
