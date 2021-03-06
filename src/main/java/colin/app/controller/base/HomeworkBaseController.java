package colin.app.controller.base;

import colin.app.common.CommonConstants;
import colin.app.core.vo.HomeworkUserInfo;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by DELL on 2015/7/10.
 */
public class HomeworkBaseController {

    /**
     * 获取基于应用程序的url绝对路径
     * @param request
     * @return
     */
    protected final String getAppBaseUrl(HttpServletRequest request, String url){
        Assert.hasLength(url, "url不能为空");
        Assert.isTrue(url.startsWith("/"), "必须以/打头");
        return request.getContextPath() + url;
    }

    /**
     * 获取HttpServletRequest
     * @return
     */
    protected HttpServletRequest getRequestObj(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取HttpSession
     * @return
     */
    protected HttpSession getSessionObj(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }
    /**
     * 获取存放在Session中的用户信息
     */
    protected HomeworkUserInfo fetchUserInfo(){
        HomeworkUserInfo homeworkUserInfo= (HomeworkUserInfo) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("userInfo");
        return homeworkUserInfo;
    }

    /**
     * 判断用户是否已经登录
     * @return
     */
    protected boolean userIsLogin(){
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute(CommonConstants.SESSION_USERINFO)==null?false:true;
    }

}
