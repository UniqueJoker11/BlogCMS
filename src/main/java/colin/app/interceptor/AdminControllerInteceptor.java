package colin.app.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by DELL on 2015/7/16.
 */
public class AdminControllerInteceptor extends HandlerInterceptorAdapter {
    @Override
    public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub
        System.out.println("回调执行");
    }
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("调用controller后进入");
    }
    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                             Object arg2) throws Exception {
        System.out.println("拦截开始：");
        System.out.println("***");
        System.out.println("&&&:"+arg2.getClass().getCanonicalName());
        System.out.println(arg2.getClass().getSimpleName());
        return false;
    }
}
