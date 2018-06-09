package com.smg.taskplatform.task.controller;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.magicube.framework.common.base.BaseController;
import com.magicube.framework.common.constant.UpmsResult;
import com.magicube.framework.common.constant.UpmsResultConstant;
import com.magicube.framework.common.utils.FatherToChildUtil;
import com.magicube.framework.common.validator.LengthValidator;
import com.magicube.framework.common.validator.NotBlankValidator;
import com.magicube.framework.upms.dao.model.UpmsUser;
import com.smg.taskplatform.task.constant.TaskConstant;
import com.smg.taskplatform.task.dao.model.TpTask;
import com.smg.taskplatform.task.dao.model.TpTaskChild;
import com.smg.taskplatform.task.dao.model.TpTaskExample;
import com.smg.taskplatform.task.operator.TaskOperator;
import com.smg.taskplatform.task.operator.UserOperator;
import com.smg.taskplatform.task.rpc.api.TpTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
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
        long time = System.currentTimeMillis();
        task.setCtime(time);

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

    @ApiOperation(value = "我的待办")
    @RequiresPermissions("tp:task:mytodo")
    @RequestMapping(value = "/mytodoindex", method = RequestMethod.GET)
    public String mytodoindex() {
        return "/manage/task/mytodoindex.jsp";
    }

    @ApiOperation(value = "我的待办列表")
    @RequiresPermissions("tp:task:mytodo")
    @RequestMapping(value = "/mytodolist", method = RequestMethod.GET)
    @ResponseBody
    public Object mytodolist(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            HttpServletRequest request) throws InvocationTargetException {

        //得到当前登录用户
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        //待办条件：任务的责任人和执行人中有操作者，任务未处于“已关闭”或“已作废”状态      
        TpTaskExample tpTaskExample = new TpTaskExample();

        //搜索条件
        if (StringUtils.isNotBlank(search)) {
            log.info("search:" + search);
            tpTaskExample.or()
                    .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                    .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                    .andResponsiblemanLike("%" + username + ",%")
                    .andTitleLike("%" + search + "%");      //任务名称
            tpTaskExample.or()
                    .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                    .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                    .andExecutorLike("%" + username + ",%")
                    .andTitleLike("%" + search + "%");      //任务名称

            if (StringUtils.isNumeric(search)) {     //任务编号
                log.info("search str is a number!");
                tpTaskExample.or()
                        .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                        .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                        .andResponsiblemanLike("%" + username + ",%")
                        .andTaskIdEqualTo(Integer.valueOf(search));   //任务编号 
                tpTaskExample.or()
                        .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                        .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                        .andExecutorLike("%" + username + ",%")
                        .andTaskIdEqualTo(Integer.valueOf(search));   //任务编号 
            }
        } else {
            tpTaskExample.or()
                    .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                    .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                    .andResponsiblemanLike("%" + username + ",%");
            tpTaskExample.or()
                    .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                    .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                    .andExecutorLike("%" + username + ",%");
        }

        //排序条件
        tpTaskExample.setOrderByClause(" task_id desc ");

        List<TpTask> tpTaskrows = tpTaskService.selectByExampleForOffsetPage(tpTaskExample, offset, limit);

        //TpTask类转换为TpTaskChild,便于数据转换
        List<TpTaskChild> rows = new ArrayList<>();
        if (!ObjectUtils.isEmpty(tpTaskrows)) {
            for (TpTask tpTask : tpTaskrows) {
                TpTaskChild tpTaskChild = new TpTaskChild();
                FatherToChildUtil.fatherToChild(tpTask, tpTaskChild);

                //转换TpTaskChild对象中相关字段信息
                tpTaskChild = taskOperator.convertTpTaskChildField(tpTaskChild);

                rows.add(tpTaskChild);
            }
        }

        long total = rows.size();

        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }
    
    @ApiOperation(value = "我的待办")
    @RequiresPermissions("tp:task:mytodo")
    @RequestMapping(value = "/mytodoindex", method = RequestMethod.GET)
    public String mytodoindex() {
        return "/manage/task/mytodoindex.jsp";
    }

    @ApiOperation(value = "我的待办列表")
    @RequiresPermissions("tp:task:mytodo")
    @RequestMapping(value = "/mytodolist", method = RequestMethod.GET)
    @ResponseBody
    public Object mytodolist(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            HttpServletRequest request) throws InvocationTargetException {

        //得到当前登录用户
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        //待办条件：任务的责任人和执行人中有操作者，任务未处于“已关闭”或“已作废”状态      
        TpTaskExample tpTaskExample = new TpTaskExample();

        //搜索条件
        if (StringUtils.isNotBlank(search)) {
            log.info("search:" + search);
            tpTaskExample.or()
                    .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                    .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                    .andResponsiblemanLike("%" + username + ",%")
                    .andTitleLike("%" + search + "%");      //任务名称
            tpTaskExample.or()
                    .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                    .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                    .andExecutorLike("%" + username + ",%")
                    .andTitleLike("%" + search + "%");      //任务名称

            if (StringUtils.isNumeric(search)) {     //任务编号
                log.info("search str is a number!");
                tpTaskExample.or()
                        .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                        .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                        .andResponsiblemanLike("%" + username + ",%")
                        .andTaskIdEqualTo(Integer.valueOf(search));   //任务编号 
                tpTaskExample.or()
                        .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                        .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                        .andExecutorLike("%" + username + ",%")
                        .andTaskIdEqualTo(Integer.valueOf(search));   //任务编号 
            }
        } else {
            tpTaskExample.or()
                    .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                    .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                    .andResponsiblemanLike("%" + username + ",%");
            tpTaskExample.or()
                    .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                    .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                    .andExecutorLike("%" + username + ",%");
        }

        //排序条件
        tpTaskExample.setOrderByClause(" task_id desc ");

        List<TpTask> tpTaskrows = tpTaskService.selectByExampleForOffsetPage(tpTaskExample, offset, limit);

        //TpTask类转换为TpTaskChild,便于数据转换
        List<TpTaskChild> rows = new ArrayList<>();
        if (!ObjectUtils.isEmpty(tpTaskrows)) {
            for (TpTask tpTask : tpTaskrows) {
                TpTaskChild tpTaskChild = new TpTaskChild();
                FatherToChildUtil.fatherToChild(tpTask, tpTaskChild);

                //转换TpTaskChild对象中相关字段信息
                tpTaskChild = taskOperator.convertTpTaskChildField(tpTaskChild);

                rows.add(tpTaskChild);
            }
        }

        long total = rows.size();

        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

}
