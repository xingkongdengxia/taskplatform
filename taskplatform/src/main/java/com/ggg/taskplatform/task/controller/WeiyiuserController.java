package com.ggg.taskplatform.task.controller;

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
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ggg.taskplatform.task.constant.TaskConstant;
import com.ggg.taskplatform.task.dao.model.TpTaskOperWeiyiuser;
import com.ggg.taskplatform.task.dao.model.TpTaskOperWeiyiuserExample;
import com.ggg.taskplatform.task.rpc.api.TpTaskOperWeiyiuserService;
import com.magicube.framework.common.base.BaseController;
import com.magicube.framework.common.constant.UpmsResult;
import com.magicube.framework.common.constant.UpmsResultConstant;
import com.magicube.framework.common.utils.FatherToChildUtil;

/**
 * 任务controller
 *
 * @author chenyangyang
 */
@Controller
@Api(value = "新建菜单")
@RequestMapping("/weiyiuserController")
public class WeiyiuserController extends BaseController {

    private static final Log log = LogFactory.getLog(WeiyiuserController.class);

    @Autowired
    private TpTaskOperWeiyiuserService tpTaskOperWeiyiuserService;

    @ApiOperation(value = "新建页面")
    @RequestMapping(value = "/weiyiuserindex", method = RequestMethod.GET)
    public String personselect(ModelMap modelMap) {
        return "/manage/weiyiuser/weiyiuserindex.jsp";
    }

    @ApiOperation(value = "新建页面列表")
    @RequiresPermissions("tp:task:mytodo")
    @RequestMapping(value = "/weiyiuserlist", method = RequestMethod.GET)
    @ResponseBody
    public Object mytodolist(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            HttpServletRequest request) throws InvocationTargetException {

       

        //待办条件：任务的责任人和执行人中有操作者，任务未处于“已关闭”或“已作废”状态      
        TpTaskOperWeiyiuserExample tpTaskOperWeiyiuserExample = new TpTaskOperWeiyiuserExample();

        //搜索条件
        if (StringUtils.isNotBlank(search)) {
            log.info("search:" + search);
            tpTaskOperWeiyiuserExample.or()
                    .andParamNameLike("%" + search + "%");      //名称
            
        } 

        //排序条件
        tpTaskOperWeiyiuserExample.setOrderByClause(" id desc ");
        List<TpTaskOperWeiyiuser> weiyiuserrows = new ArrayList<TpTaskOperWeiyiuser>();
        List<TpTaskOperWeiyiuser> tpTaskOperWeiyiuserrows = tpTaskOperWeiyiuserService
        		.selectByExampleForOffsetPage(tpTaskOperWeiyiuserExample, offset, limit);
        TpTaskOperWeiyiuser weiyiuser = null;
        for (TpTaskOperWeiyiuser tpTaskOperWeiyiuser : tpTaskOperWeiyiuserrows) {
        	weiyiuser = new TpTaskOperWeiyiuser();
        	String power = tpTaskOperWeiyiuser.getPower();
        	String weiyipower = TaskConstant.weiyipower(power);
        	FatherToChildUtil.fatherToChild(tpTaskOperWeiyiuser, weiyiuser);
        	weiyiuser.setPower(weiyipower);
        	weiyiuserrows.add(weiyiuser);
		}
        long total = weiyiuserrows.size();

        Map<String, Object> result = new HashMap<>();
        result.put("rows", weiyiuserrows);
        result.put("total", total);
        return result;
    }

    
    @ApiOperation(value = "新增数据")
    @ResponseBody
    @RequestMapping(value = "/addweiyiuser", method = RequestMethod.POST)
    public Object create(TpTaskOperWeiyiuser tpTaskOperWeiyiuser) {
        log.debug("paramName:" + tpTaskOperWeiyiuser.getParamName());
        log.debug("paramValue:" + tpTaskOperWeiyiuser.getParamValue());
        log.debug("power:" + tpTaskOperWeiyiuser.getPower());
        
        int isSuccess = 0; 
        		
        if(tpTaskOperWeiyiuser != null){
        	isSuccess = tpTaskOperWeiyiuserService.insert(tpTaskOperWeiyiuser);
        }
        
        log.info("isSuccess:" + isSuccess);
        
        if (isSuccess > 0) {
            return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
        } else {
            return new UpmsResult(UpmsResultConstant.FAILED, "保存失败！");
        } 
    }
    
    
    @ApiOperation(value = "修改数据")
    @ResponseBody
    @RequestMapping(value = "/updateweiyiuser", method = RequestMethod.POST)
    public Object updateTpTaskOperWeiyiuser(TpTaskOperWeiyiuser tpTaskOperWeiyiuser) {
        log.debug("paramName:" + tpTaskOperWeiyiuser.getParamName());
        log.debug("paramValue:" + tpTaskOperWeiyiuser.getParamValue());
        log.debug("power:" + tpTaskOperWeiyiuser.getPower());
        
        int isSuccess = 0; 
        		
        if(tpTaskOperWeiyiuser != null){
        	isSuccess = tpTaskOperWeiyiuserService.updateByPrimaryKeySelective(tpTaskOperWeiyiuser);
        }
        
        log.info("isSuccess:" + isSuccess);
        
        if (isSuccess > 0) {
            return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
        } else {
            return new UpmsResult(UpmsResultConstant.FAILED, "保存失败！");
        } 
    }
    
    @ApiOperation(value = "删除数据")
    @ResponseBody
    @RequestMapping(value = "/delweiyiuser", method = RequestMethod.GET)
    public Object delTpTaskOperWeiyiuser(Integer id) {
    	log.debug("id:" + id);
    	
    	int isSuccess = 0; 
    	
    	if(id != null){
    		isSuccess = tpTaskOperWeiyiuserService.deleteByPrimaryKey(id);
    	}
    	
    	log.info("isSuccess:" + isSuccess);
    	
    	if (isSuccess > 0) {
    		return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
    	} else {
    		return new UpmsResult(UpmsResultConstant.FAILED, "删除失败！");
    	} 
    }
    
    @ApiOperation(value = "查询单个数据")
    @ResponseBody
    @RequestMapping(value = "/findweiyiuser", method = RequestMethod.GET)
    public Object findTpTaskOperWeiyiuser(Integer id) {
    	log.debug("id:" + id);
    	Map<String, TpTaskOperWeiyiuser> map = new HashMap<String, TpTaskOperWeiyiuser>();
    	TpTaskOperWeiyiuser tpTaskOperWeiyiuser = new TpTaskOperWeiyiuser();
    	if(id != null){
    		tpTaskOperWeiyiuser = tpTaskOperWeiyiuserService.selectByPrimaryKey(id);
    	}
    	map.put("tpTaskOperWeiyiuser", tpTaskOperWeiyiuser);
    	return map;
    }
    
    
    @ApiOperation(value = "修改页面")
    @RequestMapping(value = "/findiyiuser", method = RequestMethod.GET)
    public String findupteweiyiuser(ModelMap modelMap,String mage,Integer id) {
    	log.debug("mage:" + mage);
    	log.debug("id:" + id);
    	TpTaskOperWeiyiuser tpTaskOperWeiyiuser = new TpTaskOperWeiyiuser();
    	if(id != null){
    		tpTaskOperWeiyiuser = tpTaskOperWeiyiuserService.selectByPrimaryKey(id);
    	}
    	modelMap.put("tpTaskOperWeiyiuser", tpTaskOperWeiyiuser);
    	modelMap.put("mage", mage);
    	
        return "/manage/weiyiuser/updateweiyiuser.jsp";
    }
    
    @ApiOperation(value = "新增页面")
    @RequestMapping(value = "/findiyiuseradd", method = RequestMethod.GET)
    public String addupteweiyiuser(ModelMap modelMap) {
    	TpTaskOperWeiyiuser tpTaskOperWeiyiuser = new TpTaskOperWeiyiuser();
    	tpTaskOperWeiyiuser.setPower("1");
    	modelMap.put("tpTaskOperWeiyiuser", tpTaskOperWeiyiuser);
    	modelMap.put("mage", "add");
    	
    	return "/manage/weiyiuser/addweiyiuser.jsp";
    }
}
