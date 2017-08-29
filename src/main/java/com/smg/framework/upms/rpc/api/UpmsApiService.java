package com.smg.framework.upms.rpc.api;

import com.smg.framework.upms.dao.model.UpmsLog;
import com.smg.framework.upms.dao.model.UpmsOrganization;
import com.smg.framework.upms.dao.model.UpmsOrganizationExample;
import com.smg.framework.upms.dao.model.UpmsPermission;
import com.smg.framework.upms.dao.model.UpmsRole;
import com.smg.framework.upms.dao.model.UpmsRolePermission;
import com.smg.framework.upms.dao.model.UpmsSystem;
import com.smg.framework.upms.dao.model.UpmsSystemExample;
import com.smg.framework.upms.dao.model.UpmsUser;
import com.smg.framework.upms.dao.model.UpmsUserPermission;
import java.util.List;

/**
 * upms系统接口
 *
 * @author justincai
 */
public interface UpmsApiService {

//    /**
//     * 根据用户id获取所拥有的权限(用户和角色权限合集)
//     *
//     * @param upmsUserId
//     * @return
//     */
//    List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId);
//
//    /**
//     * 根据用户id获取所拥有的权限(用户和角色权限合集)
//     *
//     * @param upmsUserId
//     * @return
//     */
//    List<UpmsPermission> selectUpmsPermissionByUpmsUserIdByCache(Integer upmsUserId);
//
//    /**
//     * 根据用户id获取所属的角色
//     *
//     * @param upmsUserId
//     * @return
//     */
//    List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId);
//
//    /**
//     * 根据用户id获取所属的角色
//     *
//     * @param upmsUserId
//     * @return
//     */
//    List<UpmsRole> selectUpmsRoleByUpmsUserIdByCache(Integer upmsUserId);
//
//    /**
//     * 根据角色id获取所拥有的权限
//     *
//     * @param upmsRoleId
//     * @return
//     */
//    List<UpmsRolePermission> selectUpmsRolePermisstionByUpmsRoleId(Integer upmsRoleId);
//
//    /**
//     * 根据用户id获取所拥有的权限
//     *
//     * @param upmsUserId
//     * @return
//     */
//    List<UpmsUserPermission> selectUpmsUserPermissionByUpmsUserId(Integer upmsUserId);

    /**
     * 根据条件获取系统数据
     *
     * @param upmsSystemExample
     * @return
     */
    List<UpmsSystem> selectUpmsSystemByExample(UpmsSystemExample upmsSystemExample);

//    /**
//     * 根据条件获取组织数据
//     *
//     * @param upmsOrganizationExample
//     * @return
//     */
//    List<UpmsOrganization> selectUpmsOrganizationByExample(UpmsOrganizationExample upmsOrganizationExample);
//
//    /**
//     * 根据username获取UpmsUser
//     *
//     * @param username
//     * @return
//     */
//    UpmsUser selectUpmsUserByUsername(String username);
//
//    /**
//     * 写入操作日志
//     *
//     * @param record
//     * @return
//     */
//    int insertUpmsLogSelective(UpmsLog record);

}
