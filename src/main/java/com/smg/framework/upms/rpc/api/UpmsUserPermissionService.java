package com.smg.framework.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import com.smg.framework.common.base.BaseService;
import com.smg.framework.upms.dao.model.UpmsUserPermission;
import com.smg.framework.upms.dao.model.UpmsUserPermissionExample;

/**
 * UpmsUserPermissionService接口
 *
 * @author justincai
 */
public interface UpmsUserPermissionService extends BaseService<UpmsUserPermission, UpmsUserPermissionExample> {

    /**
     * 用户权限
     *
     * @param datas 权限数据
     * @param id 用户id
     * @return
     */
    int permission(JSONArray datas, int id);

}
