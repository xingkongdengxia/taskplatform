package com.ggg.taskplatform.task.operator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggg.taskplatform.task.constant.TaskConstant;
import com.ggg.taskplatform.task.dao.model.TpTask;
import com.ggg.taskplatform.task.rpc.api.TpTaskService;
import com.magicube.framework.upms.rpc.api.UpmsUserService;

/**
 * 任务的
 *
 * @author justincai
 */
@Service
public class TaskplatOperator {
	 private static final Log log = LogFactory.getLog(TaskplatOperator.class);
	
	@Autowired
    private TpTaskService tpTaskService;
	@Autowired
	private UpmsUserService upmsUserService;

	public TpTaskService getTpTaskService() {
        return tpTaskService;
    }
	
	/**
	 * 指派人员
	 * @param userid
	 * @param taskId
	 */
	public boolean taskinfoupdate(String userid, int taskId) {
        log.debug("userid:" + userid);
        log.debug("taskId:" + taskId);
        TpTask tpTask = tpTaskService.selectByPrimaryKey(taskId);
        tpTask.setExecutor(userid);//指派人
        
        int i = tpTaskService.updateByPrimaryKeySelective(tpTask);
        if(i==1){
        	return true;
        }else{
        	return false;
        }
 	}
	/**
	 * 任务进度
	 * @param userid
	 * @param taskId
	 */
	public boolean taskinfook(int taskId,String taskStatus) {
		log.debug("taskId:" + taskId);
		
		TpTask tpTask = tpTaskService.selectByPrimaryKey(taskId);
		String executor = tpTask.getExecutor();
		byte parseByte = Byte.parseByte(taskStatus);
		if(parseByte == TaskConstant.TASK_STATUS_COMPLETED){//已完成
			//已办条件：任务的责任人和执行人中没有操作者，但在经办人历史序列，任务未处于“已关闭”或“已作废”状
			tpTask = this.taskinforight(tpTask,executor);
		}else if(parseByte == TaskConstant.TASK_STATUS_ABANDONED //已作废
				|| parseByte == TaskConstant.TASK_STATUS_CLOSED){//已关闭
			//办结条件：任务已经处于“已关闭”或“已作废”状态，操作者在经办人历史序列或发起人或责任人或执行人中
			tpTask = this.taskinfoGetthrough(tpTask,executor);
			
		}
		tpTask.setTaskStatus(parseByte);//状态
		
		int i = tpTaskService.updateByPrimaryKeySelective(tpTask);
		if(i==1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 任务删除
	 * @param userid
	 * @param taskId
	 */
	public boolean taskinfodel(String taskId) {
		log.debug("taskId:" + taskId);
		int id = Integer.parseInt(taskId);
		int i = tpTaskService.deleteByPrimaryKey(id);
		if(i==1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 任务已完成
	 * @param executor执行人
	 */
	public TpTask taskinforight(TpTask tpTask,String executor) {
		log.debug("executor:" + executor);
		tpTask.setResponsibleman("");//responsibleman责任人删除
		tpTask.setExecutor("");//executor执行人删除
		tpTask.setOperatorlist(executor);//operatorlist经办人历史序列(是当前执行者)
		return tpTask;
	}
	
	/**
	 * 任务已办结
	 * @param executor操作者
	 */
	public TpTask taskinfoGetthrough(TpTask tpTask,String executor) {
		log.debug("executor:" + executor);
		tpTask.setResponsibleman(executor);//responsibleman责任人删除
		tpTask.setExecutor(executor);//executor执行人删除
		tpTask.setOperatorlist(executor);//operatorlist经办人历史序列(是当前执行者)
		return tpTask;
	}
	
	
	
}
