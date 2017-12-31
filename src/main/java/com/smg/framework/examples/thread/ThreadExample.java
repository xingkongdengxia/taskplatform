package com.smg.framework.examples.thread;

import com.smg.framework.common.SpringUtil;
import com.smg.framework.thread.ExampleThread;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池使用案例
 *
 * @author justincai
 */
public class ThreadExample {

    private static final Log log = LogFactory.getLog(ThreadExample.class);

    /**
     * 另起一个线程执行
     */
    public void doThreadExample() {
        ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) SpringUtil.getBean("threadPoolTaskExecutor");
        taskExecutor.execute(new ExampleThread());

        log.info("test ThreadExample");
    }

}
