package com.magicube.framework.upms.rpc.service.impl;

import com.magicube.framework.common.base.BaseServiceImpl;
import com.magicube.framework.upms.dao.mapper.UpmsOrganizationMapper;
import com.magicube.framework.upms.dao.model.UpmsOrganization;
import com.magicube.framework.upms.dao.model.UpmsOrganizationExample;
import com.magicube.framework.upms.rpc.api.UpmsOrganizationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * UpmsOrganizationService实现
 *
 * @author justincai
 */
public class UpmsOrganizationServiceImpl extends BaseServiceImpl<UpmsOrganizationMapper, UpmsOrganization, UpmsOrganizationExample> implements UpmsOrganizationService {

    private static final Log log = LogFactory.getLog(UpmsOrganizationServiceImpl.class);

}
