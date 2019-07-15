package com.magicube.framework.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import com.magicube.framework.common.base.BaseService;
import com.magicube.framework.upms.dao.model.UpmsUserPermission;
import com.magicube.framework.upms.dao.model.UpmsUserPermissionExample;

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
