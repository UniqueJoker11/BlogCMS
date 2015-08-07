package colin.app.controller.impl;

import colin.app.common.CommonConstants;
import colin.app.controller.base.HomeworkBaseController;
import colin.app.core.pojo.Homework_User_Entity;
import colin.app.core.vo.HomeworkUserInfo;
import colin.app.core.vo.HomeworkUserMenu;
import colin.app.service.IndexService;
import colin.app.service.SigninService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/7/14.
 */
@Controller
@Scope(value = "request")
@RequestMapping("admin")
public class SigninController extends HomeworkBaseController {
    @Autowired
    private HomeworkUserInfo userInfo;
    @Autowired
    private SigninService signinService;

    /**
     * 显示signin的页面
     *
     * @return
     */
    @RequestMapping(value = "/signin.html", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String showRequestPage() {
        return CommonConstants.SIGNIN_IN;
    }

    /**
     * 方法描述：验证用户的用户名是否存在
     * 注意事项：
     *
     * @param username
     * @return
     * @Exception 异常对象
     */
    @ResponseBody
    @RequestMapping(value = "/usernamevalidate.action", method = RequestMethod.POST)
    public Boolean validateUserSigninUsername(
            @RequestParam(value = "username", required = true) String username) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (username == null || username.trim().equals("")) {
            return false;
        } else {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("user_name", username);
            Boolean validateResult = this.signinService.validateUsernameService(params);
            if (validateResult) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 方法描述：验证用户注册 注意事项：
     *
     * @param username
     * @param password
     * @return
     * @Exception 异常对象
     */
    @RequestMapping(value = "/usersignin.html", method = RequestMethod.POST)
    public String validateUserSignin(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password) {
        if (username == null || username.trim().equals("")) {
            return CommonConstants.SIGNIN_IN;
        } else {
            if (password == null || password.trim().equals("")) {
                return CommonConstants.SIGNIN_IN;
            } else {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("user_name", username);
                params.put("user_password", password);
                Map<String, Object> validateMap = signinService
                        .validateUserSigninService(params);
                if ((Boolean) validateMap.get("isExists")) {
                    Homework_User_Entity homework_user_entity = (Homework_User_Entity) validateMap.get("userEntity");
                    //初始化用户信息
                    userInfo=this.initUserInfo(homework_user_entity);
                    //存放Session
                    super.getSessionObj().setAttribute(CommonConstants.SESSION_USERINFO, userInfo);
                    //跳转主页面
                    return "redirect:"+CommonConstants.INDEX+".html";
                } else {
                    return CommonConstants.SIGNIN_IN;
                }
            }
        }

    }

    /**
     * 初始化用户的信息
     *
     * @param homework_user_entity
     * @return
     */
    public HomeworkUserInfo initUserInfo(Homework_User_Entity homework_user_entity) {
        userInfo.setUser_id(homework_user_entity.getUser_id());
        userInfo.setUser_phone(homework_user_entity.getUser_phone());
        userInfo.setUser_password(homework_user_entity.getUser_password());
        userInfo.setUser_callname(homework_user_entity.getUser_callname());
        userInfo.setUser_createtime(homework_user_entity.getUser_createtime());
        userInfo.setUser_email(homework_user_entity.getUser_email());
        userInfo.setUser_logintime(homework_user_entity.getUser_logintime());
        userInfo.setUser_name(homework_user_entity.getUser_name());
        userInfo.setUser_organize_id(homework_user_entity.getUser_organize_id());
        return userInfo;
    }
}
