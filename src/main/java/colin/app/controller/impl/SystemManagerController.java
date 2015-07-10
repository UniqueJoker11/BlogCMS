package colin.app.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ASUS on 2015/4/21.
 */
@Controller
public class SystemManagerController {
    @RequestMapping(value = "/userLogOff.html",method= RequestMethod.GET)
    public String logOffSystem(HttpServletRequest request){
        //清除Session
        return "login";
    }
    @RequestMapping(value = "/test.html")
    public String testLogin(HttpServletRequest request){
        String name=request.getParameter("username").toString();
        System.out.println(name);
        return "login";
    }
}
