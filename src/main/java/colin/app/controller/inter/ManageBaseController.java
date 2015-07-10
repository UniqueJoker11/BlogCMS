package colin.app.controller.inter;

import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by DELL on 2015/7/10.
 */
public abstract class ManageBaseController {
    /**
     * 显示请求的页面
     * @param request
     * @return
     */
    public abstract ModelAndView showRequestPage(HttpServletRequest request);

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

}
