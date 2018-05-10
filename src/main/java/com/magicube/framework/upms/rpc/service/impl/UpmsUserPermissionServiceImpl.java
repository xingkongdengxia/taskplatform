package com.magicube.framework.upms.rpc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.magicube.framework.common.annotation.BaseService;
import com.magicube.framework.common.base.BaseServiceImpl;
import com.magicube.framework.upms.dao.mapper.UpmsUserPermissionMapper;
import com.magicube.framework.upms.dao.model.UpmsUserPermission;
import com.magicube.framework.upms.dao.model.UpmsUserPermissionExample;
import com.magicube.framework.upms.rpc.api.UpmsUserPermissionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UpmsUserPermissionService实现
 *
 * @author justincai
 */
@Service
@Transactional
@BaseService
public class UpmsUserPermissionServiceImpl extends BaseServiceImpl<UpmsUserPermissionMapper, UpmsUserPermission, UpmsUserPermissionExample> implements UpmsUserPermissionService {

    private static final Log log = LogFactory.getLog(UpmsUserPermissionServiceImpl.class);

    private UpmsUserPermissionMapper upmsUserPermissionMapper;

    @Override
    public int permission(JSONArray datas, int id) {
        for (int i = 0; i < datas.size(); i++) {
            JSONObject json = datas.getJSONObject(i);
            if (json.getBoolean("checked")) {
                // 新增权限
                UpmsUserPermission upmsUserPermission = new UpmsUserPermission();
                upmsUserPermission.setUserId(id);
                upmsUserPermission.setPermissionId(json.getIntValue("id"));
                upmsUserPermission.setType(json.getByte("type"));
                upmsUserPermissionMapper.insertSelective(upmsUserPermission);
            } else {
                // 删除权限
                UpmsUserPermissionExample upmsUserPermissionExample = new UpmsUserPermissionExample();
                upmsUserPermissionExample.createCriteria()
                        .andPermissionIdEqualTo(json.getIntValue("id"))
                        .andTypeEqualTo(json.getByte("type"));
                upmsUserPermissionMapper.deleteByExample(upmsUserPermissionExample);
            }
        }
        return datas.size();
    }

    public UpmsUserPermissionMapper getUpmsUserPermissionMapper() {
        return upmsUserPermissionMapper;
    }

    public void setUpmsUserPermissionMapper(UpmsUserPermissionMapper upmsUserPermissionMapper) {
        this.upmsUserPermissionMapper = upmsUserPermissionMapper;
    }

}
