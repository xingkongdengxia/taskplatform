package com.smg.framework.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import com.smg.framework.common.base.BaseService;
import com.smg.framework.upms.dao.model.UpmsPermission;
import com.smg.framework.upms.dao.model.UpmsPermissionExample;

/**
 * UpmsPermissionService接口
 *
 * @author justincai
 */
public interface UpmsPermissionService extends BaseService<UpmsPermission, UpmsPermissionExample> {

    JSONArray getTreeByRoleId(Integer roleId);

    JSONArray getTreeByUserId(Integer usereId, Byte type);

}
