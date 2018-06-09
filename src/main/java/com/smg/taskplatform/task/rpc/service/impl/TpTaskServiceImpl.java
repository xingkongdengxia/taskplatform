package com.smg.taskplatform.task.rpc.service.impl;

import com.magicube.framework.common.annotation.BaseService;
import com.magicube.framework.common.base.BaseServiceImpl;
import com.smg.taskplatform.task.dao.mapper.TpTaskMapper;
import com.smg.taskplatform.task.dao.model.TpTask;
import com.smg.taskplatform.task.dao.model.TpTaskExample;
import com.smg.taskplatform.task.rpc.api.TpTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
* TpTaskService实现
* Created by justincai on 2018/6/9.
*/
@Service
@Transactional
@BaseService
public class TpTaskServiceImpl extends BaseServiceImpl<TpTaskMapper, TpTask, TpTaskExample> implements TpTaskService {

    private static final Log log = LogFactory.getLog(TpTaskServiceImpl.class);

    @Autowired
    TpTaskMapper tpTaskMapper;

}