package com.magicube.framework.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import com.magicube.framework.common.base.BaseService;
import com.magicube.framework.upms.dao.model.UpmsRolePermission;
import com.magicube.framework.upms.dao.model.UpmsRolePermissionExample;

/**
 * UpmsRolePermissionService接口
 *
 * @author justincai
 */
public interface UpmsRolePermissionService extends BaseService<UpmsRolePermission, UpmsRolePermissionExample> {

    /**
     * 角色权限
     *
     * @param datas 权限数据
     * @param id 角色id
     * @return
     */
    int rolePermission(JSONArray datas, int id);

}
