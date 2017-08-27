package com.smg.framework.upms.rpc.service.impl;

import com.smg.framework.common.annotation.BaseService;
import com.smg.framework.common.base.BaseServiceImpl;
import com.smg.framework.upms.dao.mapper.UpmsRoleMapper;
import com.smg.framework.upms.dao.model.UpmsRole;
import com.smg.framework.upms.dao.model.UpmsRoleExample;
import com.smg.framework.upms.rpc.api.UpmsRoleService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UpmsRoleService实现
 *
 * @author root
 */
@Service
@Transactional
@BaseService
public class UpmsRoleServiceImpl extends BaseServiceImpl<UpmsRoleMapper, UpmsRole, UpmsRoleExample> implements UpmsRoleService {

    private static final Log log = LogFactory.getLog(UpmsRoleServiceImpl.class);

    @Autowired
    UpmsRoleMapper upmsRoleMapper;

}
