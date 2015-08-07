package colin.app.controller.impl;

import java.util.HashMap;
import java.util.Map;

import colin.app.common.CommonConstants;
import colin.app.controller.base.HomeworkBaseController;
import colin.app.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@Scope(value = "request")
@RequestMapping("admin")
public class SignupController extends HomeworkBaseController {

	@Autowired
	private SignupService signupService;

	/**
	 * 
	 * 方法描述： 显示注册页面 注意事项：
	 * 
	 * @return
	 * @Exception 异常对象
	 */
	@RequestMapping(value = "signup.html",method = {RequestMethod.GET,RequestMethod.HEAD})
    public String showRequestPage(){
		return CommonConstants.SIGNIN_UP;
	}

	@RequestMapping(value = "signupUserinfo.html", method = RequestMethod.POST)
	public String signupUserinfo(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,@RequestParam(value="confirmPassword",required=true) String confirmPassword) {
		if (username == null || username.trim().equals("") || password == null
				|| password.trim().equals("")) {
			return CommonConstants.SIGNIN_UP;
		} else {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("user_name",username);
			params.put("user_password",password);
			boolean result = this.signupService.signupUserinfoService(params);
			if (result) {
				return CommonConstants.SIGNIN_IN;
			} else {
				return CommonConstants.SIGNIN_UP;
			}
		}
	}

}
