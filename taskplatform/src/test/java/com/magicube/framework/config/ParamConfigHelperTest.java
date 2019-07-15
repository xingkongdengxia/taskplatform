package com.magicube.framework.config;

import com.magicube.framework.config.dao.model.ParamConfig;
import org.junit.Test;
import static org.junit.Assert.*;
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
public class ParamConfigHelperTest {

    public ParamConfigHelperTest() {
    }

    /**
     * Test of doParam method, of class ParamConfigHelper.
     */
    @Test
    public void testDoParam() {
        System.out.println("doParam");
        ParamConfig paramConfig = new ParamConfig();
        paramConfig.setParamName("retainNumTest");
        paramConfig.setParamValue("45");
        paramConfig.setRemark("测试参数");
        ParamConfigHelper.doParam(paramConfig);

    }

    /**
     * Test of getParamValue method, of class ParamConfigHelper.
     */
    @Test
    public void testGetParamValue() {
        System.out.println("getParamValue");
        String paramName = "";
        String result = ParamConfigHelper.getParamValue(paramName);
        assertEquals(result, null);

        paramName = "retainNumTest";
        result = ParamConfigHelper.getParamValue(paramName);
        assertEquals(result, "45");

        paramName = "retainNumTest1";
        result = ParamConfigHelper.getParamValue(paramName);
        assertEquals(result, null);
    }

}
