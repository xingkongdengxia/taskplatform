package com.smg.taskplatform.task.rpc.service.impl;

import com.magicube.framework.common.annotation.BaseService;
import com.magicube.framework.common.base.BaseServiceImpl;
import com.smg.taskplatform.task.dao.mapper.TpTaskOperHistoryMapper;
import com.smg.taskplatform.task.dao.model.TpTaskOperHistory;
import com.smg.taskplatform.task.dao.model.TpTaskOperHistoryExample;
import com.smg.taskplatform.task.rpc.api.TpTaskOperHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
* TpTaskOperHistoryService实现
* Created by justincai on 2018/6/11.
*/
@Service
@Transactional
@BaseService
public class TpTaskOperHistoryServiceImpl extends BaseServiceImpl<TpTaskOperHistoryMapper, TpTaskOperHistory, TpTaskOperHistoryExample> implements TpTaskOperHistoryService {

    private static final Log log = LogFactory.getLog(TpTaskOperHistoryServiceImpl.class);

    @Autowired
    TpTaskOperHistoryMapper tpTaskOperHistoryMapper;

}