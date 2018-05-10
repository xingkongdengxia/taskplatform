package com.magicube.framework.upms.rpc.service.impl;

import com.magicube.framework.common.annotation.BaseService;
import com.magicube.framework.common.base.BaseServiceImpl;
import com.magicube.framework.upms.dao.mapper.UpmsSystemMapper;
import com.magicube.framework.upms.dao.model.UpmsSystem;
import com.magicube.framework.upms.dao.model.UpmsSystemExample;
import com.magicube.framework.upms.rpc.api.UpmsSystemService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UpmsSystemService实现
 *
 * @author justincai
 */
@Service
@Transactional
@BaseService
public class UpmsSystemServiceImpl extends BaseServiceImpl<UpmsSystemMapper, UpmsSystem, UpmsSystemExample> implements UpmsSystemService {

    private static final Log log = LogFactory.getLog(UpmsSystemServiceImpl.class);

}
