package com.magicube.framework.config.rpc.service.impl;

import com.magicube.framework.common.annotation.BaseService;
import com.magicube.framework.common.base.BaseServiceImpl;
import com.magicube.framework.config.dao.mapper.ParamConfigMapper;
import com.magicube.framework.config.dao.model.ParamConfig;
import com.magicube.framework.config.dao.model.ParamConfigExample;
import com.magicube.framework.config.rpc.api.ParamConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
* ParamConfigService实现
* Created by justincai on 2017/12/24.
*/
@Service
@Transactional
@BaseService
public class ParamConfigServiceImpl extends BaseServiceImpl<ParamConfigMapper, ParamConfig, ParamConfigExample> implements ParamConfigService {

    private static final Log log = LogFactory.getLog(ParamConfigServiceImpl.class);

    @Autowired
    ParamConfigMapper paramConfigMapper;

}