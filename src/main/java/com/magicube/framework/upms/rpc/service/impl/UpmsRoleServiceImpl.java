package com.magicube.framework.upms.rpc.service.impl;

import com.magicube.framework.common.annotation.BaseService;
import com.magicube.framework.common.base.BaseServiceImpl;
import com.magicube.framework.upms.dao.mapper.UpmsRoleMapper;
import com.magicube.framework.upms.dao.model.UpmsRole;
import com.magicube.framework.upms.dao.model.UpmsRoleExample;
import com.magicube.framework.upms.rpc.api.UpmsRoleService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

}
