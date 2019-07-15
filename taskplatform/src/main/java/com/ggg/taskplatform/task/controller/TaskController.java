package com.ggg.taskplatform.task.controller;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.magicube.framework.common.base.BaseController;
import com.magicube.framework.common.constant.UpmsResult;
import com.magicube.framework.common.constant.UpmsResultConstant;
import com.magicube.framework.common.utils.DateFormatUtil;
import com.magicube.framework.common.utils.FatherToChildUtil;
import com.magicube.framework.common.validator.LengthValidator;
import com.magicube.framework.common.validator.NotBlankValidator;
import com.magicube.framework.upms.dao.model.UpmsUser;
import com.ggg.taskplatform.task.constant.TaskConstant;
import com.ggg.taskplatform.task.dao.model.TpTask;
import com.ggg.taskplatform.task.dao.model.TpTaskChild;
import com.ggg.taskplatform.task.dao.model.TpTaskExample;
import com.ggg.taskplatform.task.dao.model.TpTaskOperHistory;
import com.ggg.taskplatform.task.dao.model.TpTaskOperHistoryChild;
import com.ggg.taskplatform.task.dao.model.TpTaskOperHistoryExample;
import com.ggg.taskplatform.task.operator.TaskOperator;
import com.ggg.taskplatform.task.operator.UserOperator;
import com.ggg.taskplatform.task.rpc.api.TpTaskOperHistoryService;
import com.ggg.taskplatform.task.rpc.api.TpTaskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
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
    
    @Autowired
    private TpTaskOperHistoryService tpTaskOperHistoryService;

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
        log.debug("taskId:" + taskchild.getTaskId());
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

        if (!StringUtils.isEmpty(taskchild.getShowStarttime())) {
            Date date = DateFormatUtil.getDateByStringDate(taskchild.getShowStarttime());
            task.setStarttime(date.getTime());
        }

        if (!StringUtils.isEmpty(taskchild.getShowEndtime())) {
            Date date = DateFormatUtil.getDateByStringDate(taskchild.getShowEndtime());
            task.setEndtime(date.getTime());
        }

        task.setTaskStatus(TaskConstant.TASK_STATUS_INPROGRESS);    //任务状态：进行中
        long time = System.currentTimeMillis();
        task.setCtime(time);
        Integer taskId = taskchild.getTaskId();
        int isSuccess = 0;
        if(taskId==null){//没有任务id为新增
        	isSuccess = tpTaskService.insertSelective(task);
        	
        	tpTaskOperHistoryService.addTpTaskOperHistory(isSuccess, "新建任务", TaskConstant.ACTION_TYPE_NEWIYBUILD);
        }else{//否者为修改
        	task.setTaskId(taskId);
        	isSuccess = tpTaskService.updateByPrimaryKeySelective(task);
        	
        	tpTaskOperHistoryService.addTpTaskOperHistory(taskId, "修改任务", TaskConstant.ACTION_TYPE_UPDATE);
        }
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

    @ApiOperation(value = "我的已办")
    @RequiresPermissions("tp:task:mytodo")
    @RequestMapping(value = "/mydoneindex", method = RequestMethod.GET)
    public String mydoneindex() {
        return "/manage/task/mydoneindex.jsp";
    }

    @ApiOperation(value = "我的已办列表")
    @RequiresPermissions("tp:task:mytodo")
    @RequestMapping(value = "/mydonelist", method = RequestMethod.GET)
    @ResponseBody
    public Object mydonelist(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            HttpServletRequest request) throws InvocationTargetException {

        //得到当前登录用户
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        //已办条件：任务的责任人和执行人中没有操作者，但在经办人历史序列，任务未处于“已关闭”或“已作废”状态
        TpTaskExample tpTaskExample = new TpTaskExample();

        //搜索条件
        if (StringUtils.isNotBlank(search)) {
            log.info("search:" + search);
            tpTaskExample.or()
                    .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                    .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                    .andResponsiblemanNotLike("%" + username + ",%")
                    .andExecutorNotLike("%" + username + ",%")
                    .andOperatorlistLike("%" + username + ",%")
                    .andTitleLike("%" + search + "%");      //任务名称

            if (StringUtils.isNumeric(search)) {     //任务编号
                log.info("search str is a number!");
                tpTaskExample.or()
                        .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                        .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                        .andResponsiblemanNotLike("%" + username + ",%")
                        .andExecutorNotLike("%" + username + ",%")
                        .andOperatorlistLike("%" + username + ",%")
                        .andTaskIdEqualTo(Integer.valueOf(search));   //任务编号 

            }
        } else {
            tpTaskExample.or()
                    .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                    .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                    .andResponsiblemanNotLike("%" + username + ",%")
                    .andExecutorNotLike("%" + username + ",%")
                    .andOperatorlistLike("%" + username + ",%");

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

    @ApiOperation(value = "我的办结")
    @RequiresPermissions("tp:task:mytodo")
    @RequestMapping(value = "/mycompletedindex", method = RequestMethod.GET)
    public String mycompletedindex() {
        return "/manage/task/mycompletedindex.jsp";
    }

    @ApiOperation(value = "我的办结列表")
    @RequiresPermissions("tp:task:mytodo")
    @RequestMapping(value = "/mycompletedlist", method = RequestMethod.GET)
    @ResponseBody
    public Object mycompletedlist(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            HttpServletRequest request) throws InvocationTargetException {

        //得到当前登录用户
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        //办结条件：任务已经处于“已关闭”或“已作废”状态，操作者在经办人历史序列或发起人或责任人或执行人中
        TpTaskExample tpTaskExample = new TpTaskExample();

        //搜索条件
        if (StringUtils.isNotBlank(search)) {
            log.info("search:" + search);
            //“已关闭”状态下
            tpTaskExample.or()
                    .andTaskStatusEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                    .andInitiatorEqualTo(username)
                    .andTitleLike("%" + search + "%");      //任务名称
            tpTaskExample.or()
                    .andTaskStatusEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                    .andResponsiblemanLike("%" + username + ",%")
                    .andTitleLike("%" + search + "%");      //任务名称
            tpTaskExample.or()
                    .andTaskStatusEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                    .andExecutorLike("%" + username + ",%")
                    .andTitleLike("%" + search + "%");      //任务名称
            tpTaskExample.or()
                    .andTaskStatusEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                    .andOperatorlistLike("%" + username + ",%")
                    .andTitleLike("%" + search + "%");      //任务名称

            //“已作废”状态下
            tpTaskExample.or()
                    .andTaskStatusEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                    .andInitiatorEqualTo(username)
                    .andTitleLike("%" + search + "%");      //任务名称
            tpTaskExample.or()
                    .andTaskStatusEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                    .andResponsiblemanLike("%" + username + ",%")
                    .andTitleLike("%" + search + "%");      //任务名称
            tpTaskExample.or()
                    .andTaskStatusEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                    .andExecutorLike("%" + username + ",%")
                    .andTitleLike("%" + search + "%");      //任务名称
            tpTaskExample.or()
                    .andTaskStatusEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                    .andOperatorlistLike("%" + username + ",%")
                    .andTitleLike("%" + search + "%");      //任务名称

            if (StringUtils.isNumeric(search)) {     //任务编号
                log.info("search str is a number!");
                //“已关闭”状态下
                tpTaskExample.or()
                        .andTaskStatusEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                        .andInitiatorEqualTo(username)
                        .andTaskIdEqualTo(Integer.valueOf(search));   //任务编号
                tpTaskExample.or()
                        .andTaskStatusEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                        .andResponsiblemanLike("%" + username + ",%")
                        .andTaskIdEqualTo(Integer.valueOf(search));   //任务编号
                tpTaskExample.or()
                        .andTaskStatusEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                        .andExecutorLike("%" + username + ",%")
                        .andTaskIdEqualTo(Integer.valueOf(search));   //任务编号
                tpTaskExample.or()
                        .andTaskStatusEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                        .andOperatorlistLike("%" + username + ",%")
                        .andTaskIdEqualTo(Integer.valueOf(search));   //任务编号

                //“已作废”状态下
                tpTaskExample.or()
                        .andTaskStatusEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                        .andInitiatorEqualTo(username)
                        .andTaskIdEqualTo(Integer.valueOf(search));   //任务编号
                tpTaskExample.or()
                        .andTaskStatusEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                        .andResponsiblemanLike("%" + username + ",%")
                        .andTaskIdEqualTo(Integer.valueOf(search));   //任务编号
                tpTaskExample.or()
                        .andTaskStatusEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                        .andExecutorLike("%" + username + ",%")
                        .andTaskIdEqualTo(Integer.valueOf(search));   //任务编号
                tpTaskExample.or()
                        .andTaskStatusEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                        .andOperatorlistLike("%" + username + ",%")
                        .andTaskIdEqualTo(Integer.valueOf(search));   //任务编号                                    

            }
        } else {
            //“已关闭”状态下
            tpTaskExample.or()
                    .andTaskStatusEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                    .andInitiatorEqualTo(username);
            tpTaskExample.or()
                    .andTaskStatusEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                    .andResponsiblemanLike("%" + username + ",%");
            tpTaskExample.or()
                    .andTaskStatusEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                    .andExecutorLike("%" + username + ",%");
            tpTaskExample.or()
                    .andTaskStatusEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                    .andOperatorlistLike("%" + username + ",%");
            //“已作废”状态下
            tpTaskExample.or()
                    .andTaskStatusEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                    .andInitiatorEqualTo(username);
            tpTaskExample.or()
                    .andTaskStatusEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                    .andResponsiblemanLike("%" + username + ",%");
            tpTaskExample.or()
                    .andTaskStatusEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                    .andExecutorLike("%" + username + ",%");
            tpTaskExample.or()
                    .andTaskStatusEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                    .andOperatorlistLike("%" + username + ",%");

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

    @ApiOperation(value = "我的待阅")
    @RequiresPermissions("tp:task:mytodo")
    @RequestMapping(value = "/myforreadingindex", method = RequestMethod.GET)
    public String myforreadingindex() {
        return "/manage/task/myforreadingindex.jsp";
    }

    @ApiOperation(value = "我的待阅列表")
    @RequiresPermissions("tp:task:mytodo")
    @RequestMapping(value = "/myforreadinglist", method = RequestMethod.GET)
    @ResponseBody
    public Object myforreadinglist(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            HttpServletRequest request) throws InvocationTargetException {

        //得到当前登录用户
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        //待阅条件：操作者在抄送人中，任务未处于“已关闭”或“已作废”状态   
        TpTaskExample tpTaskExample = new TpTaskExample();

        //搜索条件
        if (StringUtils.isNotBlank(search)) {
            log.info("search:" + search);
            tpTaskExample.or()
                    .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                    .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                    .andCcLike("%" + username + ",%")
                    .andTitleLike("%" + search + "%");      //任务名称            

            if (StringUtils.isNumeric(search)) {     //任务编号
                log.info("search str is a number!");
                tpTaskExample.or()
                        .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                        .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                        .andCcLike("%" + username + ",%")
                        .andTaskIdEqualTo(Integer.valueOf(search));   //任务编号 

            }
        } else {
            tpTaskExample.or()
                    .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_CLOSED)
                    .andTaskStatusNotEqualTo(TaskConstant.TASK_STATUS_ABANDONED)
                    .andCcLike("%" + username + ",%");
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

    @ApiOperation(value = "任务详细信息")
    @RequiresPermissions("tp:task:mytodo")
    @RequestMapping(value = "/taskinfo", method = RequestMethod.GET)
    public String taskinfo(
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
        return "/manage/task/taskinfo.jsp";
    }
    
    @ApiOperation(value = "任务历史")
    @RequiresPermissions("tp:task:mytodo")
    @RequestMapping(value = "/allHistorytask", method = RequestMethod.GET)
    public String allHistorytask() {
        return "/manage/task/allHistorytask.jsp";
    }
    
    @ApiOperation(value = "任务历史列表")
    @RequiresPermissions("tp:task:mytodo")
    @RequestMapping(value = "/allHistorytaskList", method = RequestMethod.GET)
    @ResponseBody
    public Object allHistorytasklist(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            HttpServletRequest request) throws InvocationTargetException {

        //办结条件
        TpTaskOperHistoryExample tpTaskOperHistoryExample = new TpTaskOperHistoryExample();
        TpTaskExample tpTaskExample = new TpTaskExample();

        //搜索条件
        if (StringUtils.isNotBlank(search)) {
            log.info("search:" + search);
           
            tpTaskExample.or()
            			 .andTitleLike("%"+search+"%");//根据任务名称获取任务编号
            List<Integer> values = new ArrayList<Integer>();
            List<TpTask> list = tpTaskService.selectByExample(tpTaskExample);
            for (TpTask task : list) {
            	Integer taskId = task.getTaskId();
            	values.add(taskId);
			}
            
            tpTaskOperHistoryExample.or().andTaskIdIn(values);//根据任务id查询
            
        }

        //排序条件
        tpTaskOperHistoryExample.setOrderByClause(" id asc ");
        List<TpTaskOperHistory> list = tpTaskOperHistoryService.selectByExampleForOffsetPage(tpTaskOperHistoryExample, offset, limit);
        
        
      //TpTaskOperHistory类转换为TpTaskOperHistoryChild,便于数据转换
        List<TpTaskOperHistoryChild> rows = new ArrayList<>();
        if (!ObjectUtils.isEmpty(list)) {
            for (TpTaskOperHistory tpTaskOperHistory : list) {
            	TpTaskOperHistoryChild tpTaskOperHistoryChild = new TpTaskOperHistoryChild();
                FatherToChildUtil.fatherToChild(tpTaskOperHistory, tpTaskOperHistoryChild);

                //转换TpTaskChild对象中相关字段信息
                tpTaskOperHistoryChild = taskOperator.convertTptpTaskOperHistoryChildField(tpTaskOperHistoryChild);

                rows.add(tpTaskOperHistoryChild);
            }
        }
     
        long total = rows.size();

        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }
}
