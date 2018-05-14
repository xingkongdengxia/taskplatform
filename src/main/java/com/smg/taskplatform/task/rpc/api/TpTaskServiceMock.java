package com.smg.taskplatform.task.rpc.api;

import com.magicube.framework.common.base.BaseServiceMock;
import com.smg.taskplatform.task.dao.mapper.TpTaskMapper;
import com.smg.taskplatform.task.dao.model.TpTask;
import com.smg.taskplatform.task.dao.model.TpTaskExample;

/**
* 降级实现TpTaskService接口
* Created by justincai on 2018/5/11.
*/
public class TpTaskServiceMock extends BaseServiceMock<TpTaskMapper, TpTask, TpTaskExample> implements TpTaskService {

}
