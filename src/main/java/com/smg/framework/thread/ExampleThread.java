package com.smg.framework.thread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Example的Thread类
 *
 * @author justincai
 */
public class ExampleThread implements Runnable {
    
    private static final Log log = LogFactory.getLog(ExampleThread.class);    
    
    public ExampleThread() {
        
    }    
    
    @Override
    public void run() {
        log.info("This is a ExampleThread!");
        
    }
    
}
