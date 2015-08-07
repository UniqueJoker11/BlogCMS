package colin.app.controller.impl;

import colin.app.common.CommonConstants;
import colin.app.controller.base.HomeworkBaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ASUS on 2015/4/21.
 * 系统退出登录
 */
@Controller
@Scope(value = "request")
@RequestMapping("admin")
public class SignoutController extends HomeworkBaseController {

    /**
     * 显示请求的页面
     *
     * @return
     */
    @RequestMapping(value = "/signout.html",method= RequestMethod.GET)
    public String showRequestPage() {
        //清除Session
        super.getSessionObj().removeAttribute(CommonConstants.SESSION_USERINFO);
        //返回登录页面
        return CommonConstants.SIGNIN_IN;
    }
}
