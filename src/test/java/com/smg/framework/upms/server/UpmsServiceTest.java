package com.smg.framework.upms.server;

import com.smg.framework.common.SpringUtil;
import com.smg.framework.upms.dao.mapper.UpmsSystemMapper;
import com.smg.framework.upms.dao.mapper.UpmsUserMapper;
import com.smg.framework.upms.dao.model.UpmsSystem;
import com.smg.framework.upms.dao.model.UpmsSystemExample;
import com.smg.framework.upms.dao.model.UpmsUserExample;
import com.smg.framework.upms.rpc.api.UpmsApiService;
import com.smg.framework.upms.rpc.api.UpmsSystemService;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * upms单元测试
 *
 * @author justincai
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:applicationContext.xml"
})
@Transactional
public class UpmsServiceTest {

    @Autowired
    private UpmsSystemService upmsSystemService;

    @Autowired
    private UpmsSystemMapper upmsSystemMapper;

//    @Autowired
//    private UpmsApiService upmsApiService;
    @Autowired
    private UpmsUserMapper upmsUserMapper;

    @Test
    public void testUpmsSystemService() {
        upmsSystemService.initMapper();
        int count = upmsSystemService.countByExample(new UpmsSystemExample());
        System.out.println(count);
    }

    @Test
    public void testUpmsSystemMapper() {
        long count = upmsSystemMapper.countByExample(new UpmsSystemExample());
        System.out.println("testUpmsSystemMapper测试结果：" + count);
    }

    @Test
    public void testUpmsApiService() {
        UpmsApiService upmsApiService = (UpmsApiService) SpringUtil.getBean("upmsApiService");
        List<UpmsSystem> upmsSystems = upmsApiService.selectUpmsSystemByExample(new UpmsSystemExample());
        System.out.println("testUpmsApiService测试结果：" + upmsSystems.size());
    }

    @Test
    public void testUpmsUserMapper() {
        long count = upmsUserMapper.countByExample(new UpmsUserExample());
        System.out.println("testUpmsUserMapper测试结果：" + count);
    }

}
