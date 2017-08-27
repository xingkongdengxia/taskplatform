package com.smg.framework.upms.rpc.service.impl;

import com.smg.framework.common.base.BaseServiceImpl;
import com.smg.framework.upms.dao.mapper.UpmsOrganizationMapper;
import com.smg.framework.upms.dao.model.UpmsOrganization;
import com.smg.framework.upms.dao.model.UpmsOrganizationExample;
import com.smg.framework.upms.rpc.api.UpmsOrganizationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * UpmsOrganizationService实现
 *
 * @author justincai
 */
public class UpmsOrganizationServiceImpl extends BaseServiceImpl<UpmsOrganizationMapper, UpmsOrganization, UpmsOrganizationExample> implements UpmsOrganizationService {

    private static final Log log = LogFactory.getLog(UpmsOrganizationServiceImpl.class);

    @Autowired
    UpmsOrganizationMapper upmsOrganizationMapper;

}
