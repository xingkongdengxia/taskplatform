package com.magicube.framework.upms.rpc.service.impl;

import com.magicube.framework.common.annotation.BaseService;
import com.magicube.framework.common.base.BaseServiceImpl;
import com.magicube.framework.upms.dao.mapper.UpmsUserRoleMapper;
import com.magicube.framework.upms.dao.model.UpmsUserRole;
import com.magicube.framework.upms.dao.model.UpmsUserRoleExample;
import com.magicube.framework.upms.rpc.api.UpmsUserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UpmsUserRoleService实现
 *
 * @author justincai
 */
@Service
@Transactional
@BaseService
public class UpmsUserRoleServiceImpl extends BaseServiceImpl<UpmsUserRoleMapper, UpmsUserRole, UpmsUserRoleExample> implements UpmsUserRoleService {

    private static final Log log = LogFactory.getLog(UpmsUserRoleServiceImpl.class);

    private UpmsUserRoleMapper upmsUserRoleMapper;

    @Override
    public int role(String[] roleIds, int id) {
        int result = 0;
        // 删除旧记录
        UpmsUserRoleExample upmsUserRoleExample = new UpmsUserRoleExample();
        upmsUserRoleExample.createCriteria()
                .andUserIdEqualTo(id);
        upmsUserRoleMapper.deleteByExample(upmsUserRoleExample);
        // 增加新记录
        if (null != roleIds) {
            for (String roleId : roleIds) {
                if (StringUtils.isBlank(roleId)) {
                    continue;
                }
                UpmsUserRole upmsUserRole = new UpmsUserRole();
                upmsUserRole.setUserId(id);
                upmsUserRole.setRoleId(NumberUtils.toInt(roleId));
                result = upmsUserRoleMapper.insertSelective(upmsUserRole);
            }
        }
        return result;
    }

    public UpmsUserRoleMapper getUpmsUserRoleMapper() {
        return upmsUserRoleMapper;
    }

    public void setUpmsUserRoleMapper(UpmsUserRoleMapper upmsUserRoleMapper) {
        this.upmsUserRoleMapper = upmsUserRoleMapper;
    }
    
    

}
