package colin.app.service;

import java.util.Map;

import colin.app.common.CommonUtils;
import colin.app.core.dao.decorate.SignupDao;
import colin.app.core.pojo.Homework_User_Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SignupService {

	@Autowired
	private SignupDao signupDao;
	/**
	 * 
	 * 方法描述：注册用户的信息   
	 * 注意事项：    
	 * @param params
	 * @return 
	 * @Exception 异常对象
	 */
	public boolean signupUserinfoService(Map<String,Object> params){
        Homework_User_Entity homework_user_entity=new Homework_User_Entity();
		homework_user_entity.setUser_id(CommonUtils.generateUUID());
		homework_user_entity.setUser_name(params.get("username").toString());
		homework_user_entity.setUser_password(params.get("password").toString());
		return this.signupDao.signupUserinfo(homework_user_entity);
	}
}
