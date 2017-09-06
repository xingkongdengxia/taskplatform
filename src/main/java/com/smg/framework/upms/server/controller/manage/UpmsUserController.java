package com.smg.framework.upms.server.controller.manage;

import com.alibaba.fastjson.JSONArray;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.google.common.io.Files;
import com.smg.framework.common.base.BaseController;
import com.smg.framework.common.constant.UpmsResult;
import com.smg.framework.common.constant.UpmsResultConstant;
import com.smg.framework.common.utils.MD5Util;
import com.smg.framework.common.utils.PropertiesFileUtil;
import com.smg.framework.common.validator.LengthValidator;
import com.smg.framework.common.validator.NotNullValidator;
import com.smg.framework.upms.dao.model.UpmsOrganization;
import com.smg.framework.upms.dao.model.UpmsOrganizationExample;
import com.smg.framework.upms.dao.model.UpmsRole;
import com.smg.framework.upms.dao.model.UpmsRoleExample;
import com.smg.framework.upms.dao.model.UpmsUser;
import com.smg.framework.upms.dao.model.UpmsUserExample;
import com.smg.framework.upms.dao.model.UpmsUserOrganization;
import com.smg.framework.upms.dao.model.UpmsUserOrganizationExample;
import com.smg.framework.upms.dao.model.UpmsUserRole;
import com.smg.framework.upms.dao.model.UpmsUserRoleExample;
import com.smg.framework.upms.rpc.api.UpmsOrganizationService;
import com.smg.framework.upms.rpc.api.UpmsRoleService;
import com.smg.framework.upms.rpc.api.UpmsUserOrganizationService;
import com.smg.framework.upms.rpc.api.UpmsUserPermissionService;
import com.smg.framework.upms.rpc.api.UpmsUserRoleService;
import com.smg.framework.upms.rpc.api.UpmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户controller
 *
 * @author justincai
 */
@Controller
@Api(value = "用户管理")
@RequestMapping("/manage/user")
public class UpmsUserController extends BaseController {

    private static final Log log = LogFactory.getLog(UpmsUserController.class);

    @Autowired
    private UpmsUserService upmsUserService;

    @Autowired
    private UpmsRoleService upmsRoleService;

    @Autowired
    private UpmsOrganizationService upmsOrganizationService;

    @Autowired
    private UpmsUserOrganizationService upmsUserOrganizationService;

    @Autowired
    private UpmsUserRoleService upmsUserRoleService;

    @Autowired
    private UpmsUserPermissionService upmsUserPermissionService;

    @ApiOperation(value = "用户首页")
    @RequiresPermissions("upms:user:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/user/index.jsp";
    }

    @ApiOperation(value = "用户组织")
    @RequiresPermissions("upms:user:organization")
    @RequestMapping(value = "/organization/{id}", method = RequestMethod.GET)
    public String organization(@PathVariable("id") int id, ModelMap modelMap) {
        // 所有组织
        List<UpmsOrganization> upmsOrganizations = upmsOrganizationService.selectByExample(new UpmsOrganizationExample());
        // 用户拥有组织
        UpmsUserOrganizationExample upmsUserOrganizationExample = new UpmsUserOrganizationExample();
        upmsUserOrganizationExample.createCriteria()
                .andUserIdEqualTo(id);
        List<UpmsUserOrganization> upmsUserOrganizations = upmsUserOrganizationService.selectByExample(upmsUserOrganizationExample);
        modelMap.put("upmsOrganizations", upmsOrganizations);
        modelMap.put("upmsUserOrganizations", upmsUserOrganizations);
        return "/manage/user/organization.jsp";
    }

    @ApiOperation(value = "用户组织")
    @RequiresPermissions("upms:user:organization")
    @RequestMapping(value = "/organization/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object organization(@PathVariable("id") int id, HttpServletRequest request) {
        String[] organizationIds = request.getParameterValues("organizationId");
        upmsUserOrganizationService.organization(organizationIds, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, "");
    }

    @ApiOperation(value = "用户角色")
    @RequiresPermissions("upms:user:role")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public String role(@PathVariable("id") int id, ModelMap modelMap) {
        // 所有角色
        List<UpmsRole> upmsRoles = upmsRoleService.selectByExample(new UpmsRoleExample());
        // 用户拥有角色
        UpmsUserRoleExample upmsUserRoleExample = new UpmsUserRoleExample();
        upmsUserRoleExample.createCriteria()
                .andUserIdEqualTo(id);
        List<UpmsUserRole> upmsUserRoles = upmsUserRoleService.selectByExample(upmsUserRoleExample);
        modelMap.put("upmsRoles", upmsRoles);
        modelMap.put("upmsUserRoles", upmsUserRoles);
        return "/manage/user/role.jsp";
    }

    @ApiOperation(value = "用户角色")
    @RequiresPermissions("upms:user:role")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object role(@PathVariable("id") int id, HttpServletRequest request) {
        String[] roleIds = request.getParameterValues("roleId");
        upmsUserRoleService.role(roleIds, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, "");
    }

    @ApiOperation(value = "用户权限")
    @RequiresPermissions("upms:user:permission")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    public String permission(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsUser user = upmsUserService.selectByPrimaryKey(id);
        modelMap.put("user", user);
        return "/manage/user/permission.jsp";
    }

    @ApiOperation(value = "用户权限")
    @RequiresPermissions("upms:user:permission")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object permission(@PathVariable("id") int id, HttpServletRequest request) {
        JSONArray datas = JSONArray.parseArray(request.getParameter("datas"));
        upmsUserPermissionService.permission(datas, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, datas.size());
    }

    @ApiOperation(value = "用户列表")
    @RequiresPermissions("upms:user:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order,
            HttpServletRequest request) {
        UpmsUserExample upmsUserExample = new UpmsUserExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            upmsUserExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            upmsUserExample.or()
                    .andRealnameLike("%" + search + "%");
            upmsUserExample.or()
                    .andUsernameLike("%" + search + "%");
        }
        List<UpmsUser> rows = upmsUserService.selectByExampleForOffsetPage(upmsUserExample, offset, limit);
        long total = upmsUserService.countByExample(upmsUserExample);

        //重新部署时之前上传的头像会丢失，需要重新拷贝到可访问目录
        //获得项目名称
        String appname = PropertiesFileUtil.getInstance().get("app.name");
        //获得文件上传基本目录
        String basepath = PropertiesFileUtil.getInstance().get("uploadfile.basepath");
        //文件实际路径
        String filePath = basepath + "/" + appname;

        for (UpmsUser upmsUser : rows) {
            //去掉敏感信息：密码、盐
            upmsUser.setPassword("");
            upmsUser.setSalt("");

            String theFilePath = filePath + upmsUser.getAvatar();
            log.debug("theFilePath:" + theFilePath);
            File from = new File(theFilePath);
            if (from.exists()) {
                try {
                    String toPath = request.getSession().getServletContext().getRealPath("/") + "WEB-INF" + upmsUser.getAvatar();
                    log.debug("toPath:" + toPath);
                    File to = new File(toPath);
                    if (!to.exists()) {
                        Files.createParentDirs(to);
                        Files.copy(from, to);
                    }

                } catch (IOException ex) {
                    log.error("文件拷贝出错！", ex);
                }
            } else {
                log.error("源文件不存在！");
            }

        }

        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "新增用户")
    @RequiresPermissions("upms:user:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/user/create.jsp";
    }

    @ApiOperation(value = "新增用户")
    @RequiresPermissions("upms:user:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(UpmsUser upmsUser) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsUser.getUsername(), new LengthValidator(1, 20, "帐号"))
                .on(upmsUser.getPassword(), new LengthValidator(5, 32, "密码"))
                .on(upmsUser.getRealname(), new NotNullValidator("姓名"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        long time = System.currentTimeMillis();
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        upmsUser.setSalt(salt);
        upmsUser.setPassword(MD5Util.MD5(upmsUser.getPassword() + upmsUser.getSalt()));
        upmsUser.setCtime(time);
        upmsUser = upmsUserService.createUser(upmsUser);
        if (null == upmsUser) {
            return new UpmsResult(UpmsResultConstant.FAILED, "帐号名已存在！");
        }
        log.info("新增用户，主键：userId=" + upmsUser.getUserId());
        return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
    }

    @ApiOperation(value = "删除用户")
    @RequiresPermissions("upms:user:delete")
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = upmsUserService.deleteByPrimaryKeys(ids);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改用户")
    @RequiresPermissions("upms:user:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsUser user = upmsUserService.selectByPrimaryKey(id);
        modelMap.put("user", user);
        return "/manage/user/update.jsp";
    }

    @ApiOperation(value = "修改用户")
    @RequiresPermissions("upms:user:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, UpmsUser upmsUser) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsUser.getUsername(), new LengthValidator(1, 20, "帐号"))
                .on(upmsUser.getRealname(), new NotNullValidator("姓名"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        // 不允许直接改密码
        upmsUser.setPassword(null);
        upmsUser.setUserId(id);
        int count = upmsUserService.updateByPrimaryKeySelective(upmsUser);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    /**
     * 用户头像上传
     *
     * @param fileUpload
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "用户头像上传")
    @RequiresPermissions("upms:user:create")
    @RequestMapping(value = "/avatarupload", method = RequestMethod.POST)
    @ResponseBody
    public Object avatarUpload(@RequestParam("file") MultipartFile fileUpload,
            HttpServletRequest request, HttpServletResponse response) {
        log.info("begin upload:开始上传头像");

        //开始上传文件到基本目录
        //获得项目名称
        String appname = PropertiesFileUtil.getInstance().get("app.name");
        //获得文件上传基本目录
        String basepath = PropertiesFileUtil.getInstance().get("uploadfile.basepath");
        //文件上传项目路径
        String resourcepath = "resources/admin/images/";

        //文件名
        String filename = "";
        //文件实际路径
        String filePath = "";
        // 判断文件是否为空  
        if (!fileUpload.isEmpty()) {
            filename = fileUpload.getOriginalFilename();
            log.debug("filename:" + filename);
            try {
                // 文件保存路径  
                filePath = basepath + "/" + appname + "/" + resourcepath;
                log.debug("filePath:" + filePath);
                // 转存文件  
                filePath += filename;
                File thefile = new File(filePath);
                //如果目录不存在，则新建
                if (!thefile.exists()) {
                    Files.createParentDirs(thefile);
                }
                fileUpload.transferTo(thefile);
            } catch (IOException | IllegalStateException e) {
                log.error("上传文件出错！", e);
                return new UpmsResult(UpmsResultConstant.FAILED, "上传文件出错！");
            }
        }

        //把文件拷贝到可访问目录
        File from = new File(filePath);
        if (from.exists()) {
            try {
                String toPath = request.getSession().getServletContext().getRealPath("/") + "WEB-INF/" + resourcepath + filename;
                log.debug("toPath:" + toPath);
                File to = new File(toPath);
                Files.createParentDirs(to);
                Files.copy(from, to);
            } catch (IOException ex) {
                log.error("文件拷贝出错！", ex);
                return new UpmsResult(UpmsResultConstant.FAILED, "文件拷贝出错！");
            }

            return new UpmsResult(UpmsResultConstant.SUCCESS, "/" + resourcepath + filename);
        } else {
            log.error("源文件不存在！");
            return new UpmsResult(UpmsResultConstant.FAILED, "源文件不存在！");
        }

    }

}
