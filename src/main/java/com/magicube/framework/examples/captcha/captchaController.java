package com.magicube.framework.examples.captcha;

import com.magicube.framework.common.base.BaseController;
import com.magicube.framework.common.constant.UpmsResult;
import com.magicube.framework.common.constant.UpmsResultConstant;
import com.magicube.framework.common.utils.CaptchaUtil;
import com.magicube.framework.common.utils.RedisUtil;
import io.swagger.annotations.Api;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 验证码Controller
 *
 * @author justincai
 */
@Controller
@Api(value = "验证码")
@RequestMapping("/captcha")
public class captchaController extends BaseController {

    private static final Log log = LogFactory.getLog(captchaController.class);

    /**
     * 验证码
     *
     * @param request
     * @param response
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/authCode", method = RequestMethod.GET)
    @ResponseBody
    public String getAuthCode(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        int width = NumberUtils.toInt(request.getParameter("width"), 100);
        int height = NumberUtils.toInt(request.getParameter("height"), 30);
        int codeCount = NumberUtils.toInt(request.getParameter("codeCount"), 4);
        int lineCount = NumberUtils.toInt(request.getParameter("lineCount"), 10);
        if (width > 1000) {
            width = 100;
        }
        if (height > 300) {
            height = 30;
        }
        if (codeCount > 10) {
            codeCount = 4;
        }
        if (lineCount > 100) {
            lineCount = 10;
        }
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Set-Cookie", "name=value; HttpOnly");//设置HttpOnly属性,防止Xss攻击 
        response.setDateHeader("Expires", 0);

        try {
            // 自定义参数
            CaptchaUtil code = new CaptchaUtil(width, height, codeCount, lineCount);
            code.write(request, response, session, "imageCode");
        } catch (IOException e) {
            log.error(e);
        }
        return "";

    }

    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public String captcha() {
        return "examples/captcha/captcha.jsp";
    }
    

    /**
     * 验证码 验证
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/checkImgCode", method = RequestMethod.POST)
    @ResponseBody
    public Object checkImgCode(HttpServletRequest request, HttpServletResponse response) {

        //1：获取用户输入的验证码(字符串)  
        String inputValidateCode = request.getParameter("validateCode");
        log.debug("inputValidateCode:" + inputValidateCode);
        //2：获取用户session中存储的本次生成的验证码信息(字符串)  
        String sessionId = request.getSession().getId();
        log.debug("sessionId:" + sessionId);
        String validateCode = RedisUtil.get(sessionId + "imageCode");

        log.debug("validateCode:" + validateCode);
        //3：判断验证码是否输入的正确  
        if (!StringUtils.isBlank(inputValidateCode) && inputValidateCode.equalsIgnoreCase(validateCode)) {
            //非空并且匹配上了  
            log.info("验证码正确！");
            return new UpmsResult(UpmsResultConstant.SUCCESS, "验证码正确！");
        } else {
            log.info("验证码不正确！");
            return new UpmsResult(UpmsResultConstant.FAILED, "验证码不正确！");
        }
    }

}
