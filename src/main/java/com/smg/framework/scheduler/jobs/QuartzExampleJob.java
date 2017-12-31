package com.smg.framework.scheduler.jobs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 封装同步部门和员工数据操作的Quartz Job类
 *
 * @author justincai
 */
public class QuartzExampleJob extends QuartzJobBean {

    private static final Log log = LogFactory.getLog(QuartzExampleJob.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        log.info("This is a QuartzExampleJob.");
    }

}
