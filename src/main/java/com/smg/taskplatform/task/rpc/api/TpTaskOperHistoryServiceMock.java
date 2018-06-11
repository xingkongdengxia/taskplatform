package com.smg.taskplatform.task.rpc.api;

import com.magicube.framework.common.base.BaseServiceMock;
import com.smg.taskplatform.task.dao.mapper.TpTaskOperHistoryMapper;
import com.smg.taskplatform.task.dao.model.TpTaskOperHistory;
import com.smg.taskplatform.task.dao.model.TpTaskOperHistoryExample;

/**
* 降级实现TpTaskOperHistoryService接口
* Created by justincai on 2018/6/11.
*/
public class TpTaskOperHistoryServiceMock extends BaseServiceMock<TpTaskOperHistoryMapper, TpTaskOperHistory, TpTaskOperHistoryExample> implements TpTaskOperHistoryService {

}
