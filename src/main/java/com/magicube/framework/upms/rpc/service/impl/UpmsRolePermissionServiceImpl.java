package com.magicube.framework.upms.rpc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.magicube.framework.common.annotation.BaseService;
import com.magicube.framework.common.base.BaseServiceImpl;
import com.magicube.framework.upms.dao.mapper.UpmsRolePermissionMapper;
import com.magicube.framework.upms.dao.model.UpmsRolePermission;
import com.magicube.framework.upms.dao.model.UpmsRolePermissionExample;
import com.magicube.framework.upms.rpc.api.UpmsRolePermissionService;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UpmsRolePermissionService实现
 *
 * @author justincai
 */
@Service
@Transactional
@BaseService
public class UpmsRolePermissionServiceImpl extends BaseServiceImpl<UpmsRolePermissionMapper, UpmsRolePermission, UpmsRolePermissionExample> implements UpmsRolePermissionService {

    private static final Log log = LogFactory.getLog(UpmsRolePermissionServiceImpl.class);

    private UpmsRolePermissionMapper upmsRolePermissionMapper;

    @Override
    public int rolePermission(JSONArray datas, int id) {
        List<Integer> deleteIds = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            JSONObject json = datas.getJSONObject(i);
            if (!json.getBoolean("checked")) {
                deleteIds.add(json.getIntValue("id"));
            } else {
                // 新增权限
                UpmsRolePermission upmsRolePermission = new UpmsRolePermission();
                upmsRolePermission.setRoleId(id);
                upmsRolePermission.setPermissionId(json.getIntValue("id"));
                upmsRolePermissionMapper.insertSelective(upmsRolePermission);
            }
        }
        // 删除权限
        if (deleteIds.size() > 0) {
            UpmsRolePermissionExample upmsRolePermissionExample = new UpmsRolePermissionExample();
            upmsRolePermissionExample.createCriteria()
                    .andPermissionIdIn(deleteIds)
                    .andRoleIdEqualTo(id);
            upmsRolePermissionMapper.deleteByExample(upmsRolePermissionExample);
        }
        return datas.size();
    }

    public UpmsRolePermissionMapper getUpmsRolePermissionMapper() {
        return upmsRolePermissionMapper;
    }

    public void setUpmsRolePermissionMapper(UpmsRolePermissionMapper upmsRolePermissionMapper) {
        this.upmsRolePermissionMapper = upmsRolePermissionMapper;
    }

}
