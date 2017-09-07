package com.smg.framework.upms.rpc.api;

import com.smg.framework.common.base.BaseService;
import com.smg.framework.upms.dao.model.UpmsUserRole;
import com.smg.framework.upms.dao.model.UpmsUserRoleExample;

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
