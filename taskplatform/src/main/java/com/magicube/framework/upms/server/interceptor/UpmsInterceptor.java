package com.magicube.framework.upms.server.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.magicube.framework.common.SpringUtil;
import com.magicube.framework.upms.dao.model.UpmsUser;
import com.magicube.framework.upms.dao.model.UpmsUserRole;
import com.magicube.framework.upms.dao.model.UpmsUserRoleExample;
import com.magicube.framework.upms.dao.model.UpmsUserRoleExample.Criteria;
import com.magicube.framework.upms.rpc.api.UpmsApiService;
import com.magicube.framework.upms.rpc.api.UpmsUserRoleService;

/**
 * 登录信息拦截器
 *
 * @author justincai
 */
public class UpmsInterceptor extends HandlerInterceptorAdapter {

    private static final Log log = LogFactory.getLog(UpmsInterceptor.class);

    private final UpmsApiService upmsApiService = (UpmsApiService) SpringUtil.getBean("upmsApiService");
    private final UpmsUserRoleService upmsUserRoleService = (UpmsUserRoleService) SpringUtil.getBean("upmsUserRoleService");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 过滤ajax
        if (null != request.getHeader("X-Requested-With") && request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        }
        // 登录信息
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);
        //request.setAttribute("upmsUser", upmsUser);
        UpmsUserRoleExample example = new UpmsUserRoleExample();
        Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(upmsUser.getUserId());
        List<UpmsUserRole> list = upmsUserRoleService.selectByExample(example);//获取角色id
        String Roleid = "";
        for (UpmsUserRole upmsUserRole : list) {
        	Roleid = Roleid +","+upmsUserRole.getRoleId();
		}
        
        //将用户信息存放到session
        HttpSession session = request.getSession();
        session.setAttribute("upmsUser", upmsUser);
        session.setAttribute("Roleid", Roleid);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
