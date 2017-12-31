package com.smg.framework.common.utils.processbar;

import com.smg.framework.common.utils.RedisUtil;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easybatch.core.job.JobParameters;
import org.easybatch.core.job.JobReport;
import org.easybatch.core.listener.JobListener;
import org.easybatch.core.listener.RecordReaderListener;
import org.easybatch.core.record.Record;
import org.springframework.util.ObjectUtils;

/**
 * 每读取一条数据时设置百分比
 *
 * @author root
 */
public class ProcessBarListener implements RecordReaderListener, JobListener {

    private static final Log log = LogFactory.getLog(ProcessBarListener.class);

    private final ProcessBarData pbd;

    //存入redis的变量名
    private String redisName;

    //上一次的百分比
    private int lastpercentnum = 0;

    //session对象
    private HttpSession session;

    public ProcessBarListener(ProcessBarData pbd) {
        this.pbd = pbd;
    }

    public ProcessBarListener(ProcessBarData pbd, String redisName, HttpSession session) {
        this.pbd = pbd;
        this.redisName = redisName;
        this.session = session;
    }

    @Override
    public void beforeRecordReading() {

    }

    @Override
    public void afterRecordReading(Record record) {
        if (!ObjectUtils.isEmpty(pbd) && !ObjectUtils.isEmpty(record)) {  //如果进度条数据对象和记录非空
            long currNum = record.getHeader().getNumber();
            pbd.setCurrNum(currNum);
            int tmpPercentNum = pbd.getCalculatePercent();
            log.debug("当前百分比：" + tmpPercentNum + "%");

            //如果session和redisName非空，则把百分比存入redis
            if (!ObjectUtils.isEmpty(session) && !StringUtils.isBlank(redisName)) {
                if (tmpPercentNum % 5 == 0) { //如果百分比是5的倍数，则把此对象存入redis（减少存入redis的次数，减少并发量）
                    if (NumberUtils.compare(lastpercentnum, tmpPercentNum) != 0) { //每个百分比只存1次，避免重复保存
                        lastpercentnum = tmpPercentNum;
                        int timeOut = (int) session.getMaxInactiveInterval();
                        RedisUtil.set(redisName, String.valueOf(tmpPercentNum), timeOut);
                        log.debug("百分比：" + tmpPercentNum + ",已经存入redis");
                    }
                }
            }

        }

    }

    @Override
    public void onRecordReadingException(Throwable throwable) {

    }

    @Override
    public void beforeJobStart(JobParameters jobParameters) {

    }

    @Override
    public void afterJobEnd(JobReport jobReport) {
        if (!ObjectUtils.isEmpty(pbd)) {  //如果进度条数据对象非空

            //处理流程结束后不管百分比是否已经100，都要设置为100，以表明进度结束
            pbd.setCurrNum(pbd.getTotalNum());
            int tmpPercentNum = pbd.getCalculatePercent();

            //如果session和redisName非空，则把OneProcessBarData此对象存入redis
            if (!ObjectUtils.isEmpty(session) && !StringUtils.isBlank(redisName)) {
                if (tmpPercentNum % 5 == 0) { //如果百分比是5的倍数，则把此对象存入redis（减少存入redis的次数，减少并发量）
                    if (NumberUtils.compare(lastpercentnum, tmpPercentNum) != 0) { //每个百分比只存1次，避免重复保存
                        lastpercentnum = tmpPercentNum;
                        int timeOut = (int) session.getMaxInactiveInterval();
                        RedisUtil.set(redisName, String.valueOf(tmpPercentNum), timeOut);
                        log.debug("百分比：" + tmpPercentNum + ",已经存入redis");
                    }
                }
            }

            log.debug("处理流程结束，当前百分比：" + tmpPercentNum + "%");

        }

    }

}
