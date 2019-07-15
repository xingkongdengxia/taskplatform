package com.magicube.framework.upms.server;

import com.magicube.framework.common.SpringUtil;
import com.magicube.framework.upms.dao.mapper.UpmsSystemMapper;
import com.magicube.framework.upms.dao.mapper.UpmsUserMapper;
import com.magicube.framework.upms.dao.model.UpmsLogExample;
import com.magicube.framework.upms.dao.model.UpmsOrganization;
import com.magicube.framework.upms.dao.model.UpmsOrganizationExample;
import com.magicube.framework.upms.dao.model.UpmsPermission;
import com.magicube.framework.upms.dao.model.UpmsRolePermission;
import com.magicube.framework.upms.dao.model.UpmsSystem;
import com.magicube.framework.upms.dao.model.UpmsSystemExample;
import com.magicube.framework.upms.dao.model.UpmsUser;
import com.magicube.framework.upms.dao.model.UpmsUserExample;
import com.magicube.framework.upms.dao.model.UpmsUserPermission;
import com.magicube.framework.upms.rpc.api.UpmsApiService;
import com.magicube.framework.upms.rpc.api.UpmsLogService;
import com.magicube.framework.upms.rpc.api.UpmsSystemService;
import java.util.List;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    @Test
    public void testUpmsSystemService() {
        UpmsSystemService upmsSystemService = (UpmsSystemService) SpringUtil.getBean("upmsSystemService");
        int count = upmsSystemService.countByExample(new UpmsSystemExample());
        System.out.println("testUpmsSystemService测试结果：" + count);

        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria()
                .andStatusEqualTo((byte) 1);
        List<UpmsSystem> upmsSystems = upmsSystemService.selectByExample(new UpmsSystemExample());
        for (UpmsSystem upmsSystem : upmsSystems) {
            System.out.println("testUpmsSystemService测试结果upmsSystems：" + upmsSystem.getName());
        }
    }

    @Test
    public void testUpmsSystemMapper() {
        UpmsSystemMapper upmsSystemMapper = (UpmsSystemMapper) SpringUtil.getBean("upmsSystemMapper");
        long count = upmsSystemMapper.countByExample(new UpmsSystemExample());
        System.out.println("testUpmsSystemMapper测试结果：" + count);
    }

    @Test
    public void testUpmsApiService() {
        UpmsApiService upmsApiService = (UpmsApiService) SpringUtil.getBean("upmsApiService");

        //upmsUserMapper
        UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername("admin");
        System.out.println("testUpmsApiService测试结果 upmsUser：" + upmsUser.getRealname());

        //upmsApiMapper
        List<UpmsPermission> upmsPermissions = upmsApiService.selectUpmsPermissionByUpmsUserId(1);
        System.out.println("testUpmsApiService测试结果 upmsPermissions：" + upmsPermissions.size());

        //upmsRolePermissionMapper
        List<UpmsRolePermission> upmsRolePermissions = upmsApiService.selectUpmsRolePermisstionByUpmsRoleId(1);
        System.out.println("testUpmsApiService测试结果 upmsRolePermissions：" + upmsRolePermissions.size());

        //upmsUserPermissionMapper
        List<UpmsUserPermission> upmsUserPermissions = upmsApiService.selectUpmsUserPermissionByUpmsUserId(1);
        System.out.println("testUpmsApiService测试结果 upmsUserPermissions：" + upmsUserPermissions.size());

        //upmsSystemMapper
        List<UpmsSystem> upmsSystems = upmsApiService.selectUpmsSystemByExample(new UpmsSystemExample());
        System.out.println("testUpmsApiService测试结果 upmsSystems：" + upmsSystems.size());

        //upmsOrganizationMapper
        List<UpmsOrganization> upmsOrganizations = upmsApiService.selectUpmsOrganizationByExample(new UpmsOrganizationExample());
        System.out.println("testUpmsApiService测试结果 upmsOrganizations：" + upmsSystems.size());

    }

    @Test
    public void testUpmsUserMapper() {
        UpmsUserMapper upmsUserMapper = (UpmsUserMapper) SpringUtil.getBean("upmsUserMapper");
        long count = upmsUserMapper.countByExample(new UpmsUserExample());
        System.out.println("testUpmsUserMapper测试结果：" + count);
    }

    @Test
    public void testUpmsLogService() {
        UpmsLogService upmsLogService = (UpmsLogService) SpringUtil.getBean("upmsLogService");
        int count = upmsLogService.countByExample(new UpmsLogExample());
        System.out.println("testUpmsLogService测试结果：" + count);
    }

    @Test
    public void testShiroFilter() {
        AbstractShiroFilter shiroFilter = (AbstractShiroFilter) SpringUtil.getBean("shiroFilter");

        System.out.println("testShiroFilter测试结果：" + shiroFilter.toString());
    }

}
