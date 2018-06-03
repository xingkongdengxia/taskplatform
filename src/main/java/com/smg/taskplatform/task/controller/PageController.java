package com.smg.taskplatform.task.controller;

import com.magicube.framework.common.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 任务controller
 *
 * @author justincai
 */
@Controller
@Api(value = "任务管理")
@RequestMapping("/page")
public class PageController extends BaseController {

    private static final Log log = LogFactory.getLog(PageController.class);

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
