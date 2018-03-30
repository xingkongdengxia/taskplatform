package com.magicube.framework.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring工具
 *
 * @author justincai
 */
public class SpringUtil {

    private static final Log log = LogFactory.getLog(SpringUtil.class);
    
    private static final ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

    public static Object getBean(String beanName) {
        log.info("get object from spring,beanName：" + beanName);
        return ctx.getBean(beanName);
    }

}
