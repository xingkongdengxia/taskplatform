package com.smg.framework.examples.pagination;

import com.smg.framework.common.SpringUtil;
import com.smg.framework.pagination.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *分页功能-样例:Controller类，SpringMVC的控制类，提供该业务的业务逻辑
 * @author justincai
 */
@Controller
@RequestMapping("/pagination")
public class PageController {

    private static final Log log = LogFactory.getLog(PageController.class);
    
    @RequestMapping("/list")
    public String list(@RequestParam("p") int pageNum,ModelMap model) throws InstantiationException, IllegalAccessException {
        if(pageNum==0) {
            pageNum = 1;  //获取页码，默认1
        }
        
        PageService ps = (PageService)SpringUtil.getBean("pageService");
        
        Page page = ps.getActionSettingPage(pageNum);
        
        model.addAttribute("page", page);
        
        return "examples/pagination/list.jsp";
    }

}
