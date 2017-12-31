package com.smg.framework.examples.thread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author justincai
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:applicationContext.xml"
})
@Transactional
public class ThreadExampleTest {

    public ThreadExampleTest() {
    }

    /**
     * Test of doThreadExample method, of class ThreadExample.
     */
    @Test
    public void testDoThreadExample() {
        System.out.println("doThreadExample");
        ThreadExample instance = new ThreadExample();
        instance.doThreadExample();

    }

}
