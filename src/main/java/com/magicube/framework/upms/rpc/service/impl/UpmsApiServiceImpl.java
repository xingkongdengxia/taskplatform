package com.magicube.framework.upms.rpc.service.impl;

import com.magicube.framework.upms.rpc.mapper.UpmsApiMapper;
import com.magicube.framework.upms.dao.mapper.UpmsLogMapper;
import com.magicube.framework.upms.dao.mapper.UpmsOrganizationMapper;
import com.magicube.framework.upms.dao.mapper.UpmsRolePermissionMapper;
import com.magicube.framework.upms.dao.mapper.UpmsSystemMapper;
import com.magicube.framework.upms.dao.mapper.UpmsUserMapper;
import com.magicube.framework.upms.dao.mapper.UpmsUserPermissionMapper;
import com.magicube.framework.upms.dao.model.UpmsLog;
import com.magicube.framework.upms.dao.model.UpmsOrganization;
import com.magicube.framework.upms.dao.model.UpmsOrganizationExample;
import com.magicube.framework.upms.dao.model.UpmsPermission;
import com.magicube.framework.upms.dao.model.UpmsRole;
import com.magicube.framework.upms.dao.model.UpmsRolePermission;
import com.magicube.framework.upms.dao.model.UpmsRolePermissionExample;
import com.magicube.framework.upms.dao.model.UpmsSystem;
import com.magicube.framework.upms.dao.model.UpmsSystemExample;
import com.magicube.framework.upms.dao.model.UpmsUser;
import com.magicube.framework.upms.dao.model.UpmsUserExample;
import com.magicube.framework.upms.dao.model.UpmsUserPermission;
import com.magicube.framework.upms.dao.model.UpmsUserPermissionExample;
import com.magicube.framework.upms.rpc.api.UpmsApiService;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UpmsApiService实现
 *
 * @author justincai
 */
@Service
@Transactional
public class UpmsApiServiceImpl implements UpmsApiService {

    private static final Log log = LogFactory.getLog(UpmsApiServiceImpl.class);

    private UpmsUserMapper upmsUserMapper;

    private UpmsApiMapper upmsApiMapper;

    private UpmsRolePermissionMapper upmsRolePermissionMapper;

    private UpmsUserPermissionMapper upmsUserPermissionMapper;

    private UpmsSystemMapper upmsSystemMapper;

    private UpmsOrganizationMapper upmsOrganizationMapper;

    private UpmsLogMapper upmsLogMapper;

    public UpmsUserMapper getUpmsUserMapper() {
        return upmsUserMapper;
    }

    public void setUpmsUserMapper(UpmsUserMapper upmsUserMapper) {
        this.upmsUserMapper = upmsUserMapper;
    }

    public UpmsApiMapper getUpmsApiMapper() {
        return upmsApiMapper;
    }

    public void setUpmsApiMapper(UpmsApiMapper upmsApiMapper) {
        this.upmsApiMapper = upmsApiMapper;
    }

    public UpmsRolePermissionMapper getUpmsRolePermissionMapper() {
        return upmsRolePermissionMapper;
    }

    public void setUpmsRolePermissionMapper(UpmsRolePermissionMapper upmsRolePermissionMapper) {
        this.upmsRolePermissionMapper = upmsRolePermissionMapper;
    }

    public UpmsUserPermissionMapper getUpmsUserPermissionMapper() {
        return upmsUserPermissionMapper;
    }

    public void setUpmsUserPermissionMapper(UpmsUserPermissionMapper upmsUserPermissionMapper) {
        this.upmsUserPermissionMapper = upmsUserPermissionMapper;
    }

    public UpmsOrganizationMapper getUpmsOrganizationMapper() {
        return upmsOrganizationMapper;
    }

    public void setUpmsOrganizationMapper(UpmsOrganizationMapper upmsOrganizationMapper) {
        this.upmsOrganizationMapper = upmsOrganizationMapper;
    }

    public UpmsLogMapper getUpmsLogMapper() {
        return upmsLogMapper;
    }

    public void setUpmsLogMapper(UpmsLogMapper upmsLogMapper) {
        this.upmsLogMapper = upmsLogMapper;
    }

    public UpmsSystemMapper getUpmsSystemMapper() {
        return upmsSystemMapper;
    }

    public void setUpmsSystemMapper(UpmsSystemMapper upmsSystemMapper) {
        this.upmsSystemMapper = upmsSystemMapper;
    }

    /**
     * 根据用户id获取所拥有的权限
     *
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId) {
        // 用户不存在或锁定状态
        UpmsUser upmsUser = upmsUserMapper.selectByPrimaryKey(upmsUserId);
        if (null == upmsUser || 1 == upmsUser.getLocked()) {
            log.info("selectUpmsPermissionByUpmsUserId : upmsUserId=" + upmsUserId);
            return null;
        }
        List<UpmsPermission> upmsPermissions = upmsApiMapper.selectUpmsPermissionByUpmsUserId(upmsUserId);
        return upmsPermissions;
    }

    /**
     * 根据用户id获取所拥有的权限
     *
     * @param upmsUserId
     * @return
     */
    @Override
    @Cacheable(value = "upms-rpc-service-ehcache", key = "'selectUpmsPermissionByUpmsUserId_' + #upmsUserId")
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserIdByCache(Integer upmsUserId) {
        return selectUpmsPermissionByUpmsUserId(upmsUserId);
    }

    /**
     * 根据用户id获取所属的角色
     *
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId) {
        // 用户不存在或锁定状态
        UpmsUser upmsUser = upmsUserMapper.selectByPrimaryKey(upmsUserId);
        if (null == upmsUser || 1 == upmsUser.getLocked()) {
            log.info("selectUpmsRoleByUpmsUserId : upmsUserId=" + upmsUserId);
            return null;
        }
        List<UpmsRole> upmsRoles = upmsApiMapper.selectUpmsRoleByUpmsUserId(upmsUserId);
        return upmsRoles;
    }

    /**
     * 根据用户id获取所属的角色
     *
     * @param upmsUserId
     * @return
     */
    @Override
    @Cacheable(value = "upms-rpc-service-ehcache", key = "'selectUpmsRoleByUpmsUserId_' + #upmsUserId")
    public List<UpmsRole> selectUpmsRoleByUpmsUserIdByCache(Integer upmsUserId) {
        return selectUpmsRoleByUpmsUserId(upmsUserId);
    }

    /**
     * 根据角色id获取所拥有的权限
     *
     * @param upmsRoleId
     * @return
     */
    @Override
    public List<UpmsRolePermission> selectUpmsRolePermisstionByUpmsRoleId(Integer upmsRoleId) {
        UpmsRolePermissionExample upmsRolePermissionExample = new UpmsRolePermissionExample();
        upmsRolePermissionExample.createCriteria()
                .andRoleIdEqualTo(upmsRoleId);
        List<UpmsRolePermission> upmsRolePermissions = upmsRolePermissionMapper.selectByExample(upmsRolePermissionExample);
        return upmsRolePermissions;
    }

    /**
     * 根据用户id获取所拥有的权限
     *
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsUserPermission> selectUpmsUserPermissionByUpmsUserId(Integer upmsUserId) {
        UpmsUserPermissionExample upmsUserPermissionExample = new UpmsUserPermissionExample();
        upmsUserPermissionExample.createCriteria()
                .andUserIdEqualTo(upmsUserId);
        List<UpmsUserPermission> upmsUserPermissions = upmsUserPermissionMapper.selectByExample(upmsUserPermissionExample);
        return upmsUserPermissions;
    }

    /**
     * 根据条件获取系统数据
     *
     * @param upmsSystemExample
     * @return
     */
    @Override
    public List<UpmsSystem> selectUpmsSystemByExample(UpmsSystemExample upmsSystemExample) {
        return upmsSystemMapper.selectByExample(upmsSystemExample);
    }

    /**
     * 根据条件获取组织数据
     *
     * @param upmsOrganizationExample
     * @return
     */
    @Override
    public List<UpmsOrganization> selectUpmsOrganizationByExample(UpmsOrganizationExample upmsOrganizationExample) {
        return upmsOrganizationMapper.selectByExample(upmsOrganizationExample);
    }

    /**
     * 根据username获取UpmsUser
     *
     * @param username
     * @return
     */
    @Override
    public UpmsUser selectUpmsUserByUsername(String username) {
        UpmsUserExample upmsUserExample = new UpmsUserExample();
        upmsUserExample.createCriteria()
                .andUsernameEqualTo(username);
        List<UpmsUser> upmsUsers = upmsUserMapper.selectByExample(upmsUserExample);
        if (null != upmsUsers && upmsUsers.size() > 0) {
            return upmsUsers.get(0);
        }
        return null;
    }

    /**
     * 写入操作日志
     *
     * @param record
     * @return
     */
    @Override
    public int insertUpmsLogSelective(UpmsLog record) {
        return upmsLogMapper.insertSelective(record);
    }
}
