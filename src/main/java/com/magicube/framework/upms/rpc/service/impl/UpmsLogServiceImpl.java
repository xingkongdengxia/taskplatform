package com.magicube.framework.upms.rpc.service.impl;

import com.magicube.framework.common.annotation.BaseService;
import com.magicube.framework.common.base.BaseServiceImpl;
import com.magicube.framework.upms.dao.mapper.UpmsLogMapper;
import com.magicube.framework.upms.dao.model.UpmsLog;
import com.magicube.framework.upms.dao.model.UpmsLogExample;
import com.magicube.framework.upms.rpc.api.UpmsLogService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UpmsLogService实现
 *
 * @author justincai
 */
@Service
@Transactional
@BaseService
public class UpmsLogServiceImpl extends BaseServiceImpl<UpmsLogMapper, UpmsLog, UpmsLogExample> implements UpmsLogService {

    private static final Log log = LogFactory.getLog(UpmsLogServiceImpl.class);

}
