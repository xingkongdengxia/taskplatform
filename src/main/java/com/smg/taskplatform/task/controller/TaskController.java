package com.smg.taskplatform.task.controller;

import com.magicube.framework.common.base.BaseController;
import com.magicube.framework.common.constant.UpmsResult;
import com.magicube.framework.common.constant.UpmsResultConstant;
import com.magicube.framework.upms.dao.model.UpmsUser;
import com.smg.taskplatform.task.dao.model.TpTaskChild;
import com.smg.taskplatform.task.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    private UserService userService;

    @ApiOperation(value = "选择联系人")
    @RequestMapping(value = "/personselect", method = RequestMethod.GET)
    public String personselect(ModelMap modelMap) {

        List<UpmsUser> userList = userService.getAllUsersExceptAdmin();
        modelMap.put("userList", userList);

        return "/manage/task/personselect.jsp";
    }

    @ApiOperation(value = "新建任务")
    @RequiresPermissions("tp:task:add")
    @RequestMapping(value = "/addtask", method = RequestMethod.GET)
    public String addtask(ModelMap modelMap) {

        //人员选择框数据
        List<UpmsUser> userList = userService.getAllUsersExceptAdmin();
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
        log.debug("showStarttime:" + taskchild.getShowStarttime().toString());
        log.debug("starttime:" + taskchild.getStarttime());
        log.debug("showEndtime:" + taskchild.getShowEndtime().toString());
        log.debug("endtime:" + taskchild.getEndtime());

        return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
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

        return "result.jsp";
    }

}
