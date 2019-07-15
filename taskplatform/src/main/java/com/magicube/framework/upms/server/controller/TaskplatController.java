package com.magicube.framework.upms.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ggg.taskplatform.task.constant.TaskConstant;
import com.ggg.taskplatform.task.dao.model.TpTask;
import com.ggg.taskplatform.task.dao.model.TpTaskChild;
import com.ggg.taskplatform.task.dao.model.TpTaskExample;
import com.ggg.taskplatform.task.dao.model.TpTaskOperHistory;
import com.ggg.taskplatform.task.operator.TaskOperator;
import com.ggg.taskplatform.task.operator.TaskplatOperator;
import com.ggg.taskplatform.task.operator.UserOperator;
import com.ggg.taskplatform.task.rpc.api.TpTaskOperHistoryService;
import com.ggg.taskplatform.task.rpc.api.TpTaskService;
import com.magicube.framework.common.base.BaseController;
import com.magicube.framework.common.constant.UpmsResult;
import com.magicube.framework.common.constant.UpmsResultConstant;
import com.magicube.framework.common.utils.FatherToChildUtil;
import com.magicube.framework.upms.dao.model.UpmsUser;

/**
 * 我的任务controller
 *
 * @author justincai
 */
@Controller
@Api(value = "任务操作")
@RequestMapping("/taskplatController")
public class TaskplatController extends BaseController{
	 private static final Log log = LogFactory.getLog(TaskplatController.class);
	 
	 	@Autowired
	    private TaskplatOperator taskplatOperator;
	 	@Autowired
	    private TaskOperator taskOperator;
	 	@Autowired
	    private TpTaskService tpTaskService;
	 	@Autowired
	    private UserOperator userOperator;
	 	@Autowired
	 	private TpTaskOperHistoryService tpTaskOperHistoryService;
	 	
	 	
	 	@ApiOperation(value = "指派人员")
	    @ResponseBody
	    @RequestMapping(value = "/updatetask", method = RequestMethod.POST)
	    public Object taskinfoupdate(String userid, String taskId) {//String userid, String taskId
	        log.debug("userid:" + userid);
	        log.debug("taskId:" + taskId);
	        int id = Integer.parseInt(taskId);
	        boolean string = taskplatOperator.taskinfoupdate(userid, id);
	        
	        if (string) {
	        	tpTaskOperHistoryService.addTpTaskOperHistory(id, "指派人员", TaskConstant.ACTION_TYPE_ASSIGN);
	            return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
	        } else {
	            return new UpmsResult(UpmsResultConstant.FAILED, "指派失败！");
	        }
	 	}
	 	@ApiOperation(value = "任务进度")
	 	@ResponseBody
	 	@RequestMapping(value = "/oktask", method = RequestMethod.POST)
	 	public Object taskinfook(String taskId,String taskStatus) {//String userid, String taskId
	 		log.debug("taskId:" + taskId);
	 		int id = Integer.parseInt(taskId);
	 		boolean string = taskplatOperator.taskinfook(id,taskStatus);
	 		if (string) {
	 			tpTaskOperHistoryService.addTpTaskOperHistory(id, TaskConstant.getTaskStatusName(Byte.parseByte(taskStatus))+"任务"
	 					, TaskConstant.gettaskinfooktoActionType(taskStatus));
	 			return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
	 		} else {
	 			return new UpmsResult(UpmsResultConstant.FAILED, "提交失败！");
	 		}
	 	}
	 	
	 	@ApiOperation(value = "任务删除")
	 	@ResponseBody
	 	@RequestMapping(value = "/deltask", method = RequestMethod.POST)
	 	public Object taskinfodel(String taskId) {//String userid, String taskId
	 		log.debug("taskId:" + taskId);
	 		
	 		boolean string = taskplatOperator.taskinfodel(taskId);
	 		if (string) {
	 			tpTaskOperHistoryService.addTpTaskOperHistory(Integer.parseInt(taskId), "任务删除" , TaskConstant.ACTION_TYPE_DELETE);
	 			return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
	 		} else {
	 			return new UpmsResult(UpmsResultConstant.FAILED, "提交失败！");
	 		}
	 	}
	 	
	 	@ApiOperation(value = "任务修改查询")
	 	@RequiresPermissions("tp:task:mytodo")
	 	@RequestMapping(value = "/taskinfoupdatesel", method = RequestMethod.GET)
	    public String taskinfoupdatesel(
	            @RequestParam(required = true, defaultValue = "0", value = "taskId") int taskId,
	            ModelMap modelMap) throws InvocationTargetException {
	        log.info("taskId:" + taskId);

	        TpTaskExample tpTaskExample = new TpTaskExample();
	        tpTaskExample.or()
	                .andTaskIdEqualTo(taskId);
	        TpTask tpTask = tpTaskService.selectFirstByExample(tpTaskExample);
	        TpTaskChild tpTaskChild = new TpTaskChild();
	        if (!ObjectUtils.isEmpty(tpTask)) {
	            FatherToChildUtil.fatherToChild(tpTask, tpTaskChild);

	            //转换TpTaskChild对象中相关字段信息
	            tpTaskChild = taskOperator.convertTpTaskChildField(tpTaskChild);
	        }

	        modelMap.put("tpTaskChild", tpTaskChild);

	        //人员选择框数据
	        List<UpmsUser> userList = userOperator.getAllUsersExceptAdmin();
	        modelMap.put("userList", userList);
	        return "/manage/task/updatetask.jsp";
	    }
	 	@ApiOperation(value = "任务历史记录删除")
	 	@ResponseBody
	 	@RequestMapping(value = "/tpTaskOperHistorydel", method = RequestMethod.GET)
	 	public Object tpTaskOperHistorydel(String id) {
	 		log.info("id:" + id);
	 		int parseInt = Integer.parseInt(id);
	 		int deleteByPrimaryKey = tpTaskOperHistoryService.deleteByPrimaryKey(parseInt);
	 		if (deleteByPrimaryKey == 1) {
	            return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
	        } else {
	            return new UpmsResult(UpmsResultConstant.FAILED, "删除失败！");
	        }
	 	}
}
