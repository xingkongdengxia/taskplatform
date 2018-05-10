package com.magicube.framework.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import com.magicube.framework.common.base.BaseService;
import com.magicube.framework.upms.dao.model.UpmsPermission;
import com.magicube.framework.upms.dao.model.UpmsPermissionExample;

/**
 * UpmsPermissionService接口
 *
 * @author justincai
 */
public interface UpmsPermissionService extends BaseService<UpmsPermission, UpmsPermissionExample> {

    JSONArray getTreeByRoleId(Integer roleId);

    JSONArray getTreeByUserId(Integer usereId, Byte type);

}
