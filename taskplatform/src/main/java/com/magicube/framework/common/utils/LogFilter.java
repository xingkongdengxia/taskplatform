package com.magicube.framework.common.utils;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.MDC;

import com.magicube.framework.upms.dao.model.UpmsUser;

public class LogFilter  implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;  
        HttpSession session= req.getSession();  
            //MDC.put("userId",DEFAULT_USERID);    
        //logger.info("test for MDC."); 
        
        
//        INSERT INTO `taskplatform`.`upms_log`(`description`, `username`, `start_time`, 
//				`spend_time`, `base_path`, `uri`, `url`, `method`, 
//				`parameter`, `user_agent`, `ip`, `result`, `permissions`) 
//VALUES ('%m', '%X{username}', '%d{yyyy MMM dd HH:mm:ss,SSS}', '%r', '%c', '%X{uri}', '%X{url}', '%X{method}',
//'%X{parameter}', '%X{user_agent}', '%X{ip}', '%X{result}', '%X{permissions}')
        UpmsUser upmsUser = (UpmsUser) session.getAttribute("upmsUser");//获取用户信息
        StringBuffer requestURL = req.getRequestURL();
        String requestURI = req.getRequestURI();
        String ip = request.getServerName();
        String method = req.getMethod();
        if(upmsUser != null){
        	 MDC.put("username",upmsUser.getUsername());//用户名
             MDC.put("uri",requestURI);//访问路径uri
             MDC.put("url",requestURL.toString());//访问路径url
             MDC.put("user_agent",upmsUser.getUserId());//用户标识（userid）
             MDC.put("ip",ip);//访问ip
             MDC.put("method",method);//访问类型
        }
       
        //应该在log4j里获取
        MDC.put("result","暂不获取");//访问result
        
        
        
        String Roleid= (String) session.getAttribute("Roleid");
        if(Roleid!=null){
        	 MDC.put("permissions",Roleid);//权限值
        }
       
        
        Enumeration<String> names = request.getParameterNames();   //获取所有参数名称列表  
        String parameter = "";
        while(names.hasMoreElements()){
        	String name =(String)names.nextElement();
        	String string = req.getParameter(name);
        	
        	parameter = parameter+" 和 "+name+"="+string;
        }
        
        if(parameter!=null){
       	 MDC.put("parameter",parameter);//参数
        }

        
       chain.doFilter(request, response); 
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
