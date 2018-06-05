package com.smg.taskplatform.task.controller;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.magicube.framework.common.base.BaseController;
import com.magicube.framework.common.constant.UpmsResult;
import com.magicube.framework.common.constant.UpmsResultConstant;
import com.magicube.framework.common.validator.LengthValidator;
import com.magicube.framework.common.validator.NotBlankValidator;
import com.magicube.framework.upms.dao.model.UpmsUser;
import com.smg.taskplatform.task.constant.TaskConstant;
import com.smg.taskplatform.task.dao.model.TpTask;
import com.smg.taskplatform.task.dao.model.TpTaskChild;
import com.smg.taskplatform.task.operator.TaskOperator;
import com.smg.taskplatform.task.operator.UserOperator;
import com.smg.taskplatform.task.rpc.api.TpTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 任务controller
 *
 * @author justincai
 */
@Controller
@Api(value = "任务管理")
@RequestMapping("/manage/task")
public class TaskController extends BaseController {

    private static final Log log = LogFactory.getLog(TaskController.class);

    @Autowired
    private UserOperator userOperator;

    @Autowired
    private TaskOperator taskOperator;

    @Autowired
    private TpTaskService tpTaskService;

    @ApiOperation(value = "选择联系人")
    @RequestMapping(value = "/personselect", method = RequestMethod.GET)
    public String personselect(ModelMap modelMap) {

        List<UpmsUser> userList = userOperator.getAllUsersExceptAdmin();
        modelMap.put("userList", userList);

        return "/manage/task/personselect.jsp";
    }

    @ApiOperation(value = "新建任务")
    @RequiresPermissions("tp:task:add")
    @RequestMapping(value = "/addtask", method = RequestMethod.GET)
    public String addtask(ModelMap modelMap) {

        //人员选择框数据
        List<UpmsUser> userList = userOperator.getAllUsersExceptAdmin();
        modelMap.put("userList", userList);

        return "/manage/task/addtask.jsp";
    }

    @ApiOperation(value = "新建任务")
    @RequiresPermissions("tp:task:add")
    @ResponseBody
    @RequestMapping(value = "/addtask", method = RequestMethod.POST)
    public Object create(TpTaskChild taskchild) {
        log.debug("taskType:" + taskchild.getTaskType());
        log.debug("priority:" + taskchild.getPriority());
        log.debug("responsibleman:" + taskchild.getResponsibleman());
        log.debug("executor:" + taskchild.getExecutor());
        log.debug("cc:" + taskchild.getCc());
        log.debug("title:" + taskchild.getTitle());
        log.debug("description:" + taskchild.getDescription());
        log.debug("showStarttime:" + taskchild.getShowStarttime());
        log.debug("starttime:" + taskchild.getStarttime());
        log.debug("showEndtime:" + taskchild.getShowEndtime());
        log.debug("endtime:" + taskchild.getEndtime());

        //检查必填项
        ComplexResult result = FluentValidator.checkAll()
                .on(taskchild.getResponsibleman(), new NotBlankValidator("责任人"))
                .on(taskchild.getTitle(), new LengthValidator(1, 1000, "任务名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.FAILED, result.getErrors());
        }

        //检查各字段长度
        result = taskOperator.checkFieldLength(taskchild);
        if (!ObjectUtils.isEmpty(result) && !result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }

        //检查开始日期与截止日期的合理性（截止日期不能早于开始日期）
        boolean startTimeBeforeEndTime = taskOperator.checkStartAndEndTime(taskchild);
        if (!startTimeBeforeEndTime) {
            return new UpmsResult(UpmsResultConstant.FAILED, "截止日期不能早于开始日期!");
        }

        //得到当前登录用户
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        TpTask task = new TpTask();
        task.setTitle(taskchild.getTitle());
        task.setDescription(taskchild.getDescription());
        task.setInitiator(username);    //发起人
        task.setResponsibleman(taskchild.getResponsibleman());
        task.setExecutor(taskchild.getExecutor());
        task.setCc(taskchild.getCc());
        task.setTaskType(taskchild.getTaskType());
        task.setTaskSource(TaskConstant.TASK_SOURCE_SELF);  //自建任务
        task.setPriority(taskchild.getPriority());
        task.setStarttime(taskchild.getStarttime());
        task.setEndtime(taskchild.getEndtime());
        task.setTaskStatus(TaskConstant.TASK_STATUS_INPROGRESS);    //任务状态：进行中

        int isSuccess = tpTaskService.insertSelective(task);
        log.info("isSuccess:" + isSuccess);
        if (isSuccess > 0) {
            return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
        } else {
            return new UpmsResult(UpmsResultConstant.FAILED, "保存失败！");
        }

    }

    /**
     * 转入结果页面
     *
     * @param message 显示信息
     * @param modelMap
     * @return
     */
    @ApiOperation(value = "结果页面")
    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String result(
            @RequestParam(required = true, defaultValue = "", value = "message") String message,
            ModelMap modelMap) {

        modelMap.put("message", message);

        return "/manage/task/result.jsp";
    }

}
