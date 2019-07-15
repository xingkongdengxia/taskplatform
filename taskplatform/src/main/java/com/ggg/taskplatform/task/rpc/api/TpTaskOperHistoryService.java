package com.ggg.taskplatform.task.rpc.api;

import com.magicube.framework.common.base.BaseService;
import com.ggg.taskplatform.task.dao.model.TpTaskOperHistory;
import com.ggg.taskplatform.task.dao.model.TpTaskOperHistoryExample;

/**
* TpTaskOperHistoryService接口
* Created by justincai on 2018/6/11.
*/
public interface TpTaskOperHistoryService extends BaseService<TpTaskOperHistory, TpTaskOperHistoryExample> {
	
	/**
     * 新增任务历史记录
     * @param taskId		任务ID
     * @param remark		操作描述
     * @param actionType	操作内容
     */
	public void addTpTaskOperHistory(Integer taskId,String remark,Byte actionType);
	

}