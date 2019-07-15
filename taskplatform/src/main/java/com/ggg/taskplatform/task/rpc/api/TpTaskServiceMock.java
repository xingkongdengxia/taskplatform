package com.ggg.taskplatform.task.rpc.api;

import com.magicube.framework.common.base.BaseServiceMock;
import com.ggg.taskplatform.task.dao.mapper.TpTaskMapper;
import com.ggg.taskplatform.task.dao.model.TpTask;
import com.ggg.taskplatform.task.dao.model.TpTaskExample;

/**
* 降级实现TpTaskService接口
* Created by justincai on 2018/6/9.
*/
public class TpTaskServiceMock extends BaseServiceMock<TpTaskMapper, TpTask, TpTaskExample> implements TpTaskService {

}
