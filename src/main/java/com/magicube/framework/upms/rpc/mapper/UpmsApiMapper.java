package com.magicube.framework.upms.rpc.mapper;

import com.magicube.framework.upms.dao.model.UpmsPermission;
import com.magicube.framework.upms.dao.model.UpmsRole;
import java.util.List;

/**
 * 用户VOMapper
 *
 * @author justincai
 */
public interface UpmsApiMapper {

    // 根据用户id获取所拥有的权限
    List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId);

    // 根据用户id获取所属的角色
    List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId);

}
