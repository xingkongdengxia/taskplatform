package com.magicube.framework.upms.server.controller.manage;

import com.magicube.framework.common.base.BaseController;
import com.magicube.framework.common.constant.UpmsResult;
import com.magicube.framework.common.constant.UpmsResultConstant;
import com.magicube.framework.upms.client.shiro.session.UpmsSessionDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 会话管理controller
 *
 * @author justincai
 */
@Controller
@Api(value = "会话管理")
@RequestMapping("/manage/session")
public class UpmsSessionController extends BaseController {

    private static final Log log = LogFactory.getLog(UpmsSessionController.class);

    @Autowired
    private UpmsSessionDao sessionDAO;

    @ApiOperation(value = "会话首页")
    @RequiresPermissions("upms:session:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/session/index.jsp";
    }

    @ApiOperation(value = "会话列表")
    @RequiresPermissions("upms:session:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit) {
        return sessionDAO.getActiveSessions(offset, limit);
    }

    @ApiOperation(value = "强制退出")
    @RequiresPermissions("upms:session:forceout")
    @RequestMapping(value = "/forceout/{ids}", method = RequestMethod.GET)
    @ResponseBody
    public Object forceout(@PathVariable("ids") String ids) {
        int count = sessionDAO.forceout(ids);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

}
