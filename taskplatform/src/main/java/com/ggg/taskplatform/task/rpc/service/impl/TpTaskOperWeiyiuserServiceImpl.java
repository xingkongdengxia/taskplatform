package com.ggg.taskplatform.task.rpc.service.impl;

import com.magicube.framework.common.annotation.BaseService;
import com.magicube.framework.common.base.BaseServiceImpl;
import com.ggg.taskplatform.task.dao.mapper.TpTaskOperWeiyiuserMapper;
import com.ggg.taskplatform.task.dao.model.TpTaskOperWeiyiuser;
import com.ggg.taskplatform.task.dao.model.TpTaskOperWeiyiuserExample;
import com.ggg.taskplatform.task.rpc.api.TpTaskOperWeiyiuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
* TpTaskOperWeiyiuserService实现
* Created by justincai on 2019/7/5.
*/
@Service
@Transactional
@BaseService
public class TpTaskOperWeiyiuserServiceImpl extends BaseServiceImpl<TpTaskOperWeiyiuserMapper, TpTaskOperWeiyiuser, TpTaskOperWeiyiuserExample> implements TpTaskOperWeiyiuserService {

    private static final Log log = LogFactory.getLog(TpTaskOperWeiyiuserServiceImpl.class);

    @Autowired
    TpTaskOperWeiyiuserMapper tpTaskOperWeiyiuserMapper;

}