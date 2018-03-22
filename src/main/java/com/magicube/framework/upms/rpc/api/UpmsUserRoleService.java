package com.magicube.framework.upms.rpc.api;

import com.magicube.framework.common.base.BaseService;
import com.magicube.framework.upms.dao.model.UpmsUserRole;
import com.magicube.framework.upms.dao.model.UpmsUserRoleExample;

/**
 * UpmsUserRoleService接口
 *
 * @author justincai
 */
public interface UpmsUserRoleService extends BaseService<UpmsUserRole, UpmsUserRoleExample> {

    /**
     * 用户角色
     *
     * @param roleIds 角色ids
     * @param id 用户id
     * @return
     */
    int role(String[] roleIds, int id);

}
