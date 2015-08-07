package colin.app.core.dao.decorate;

import colin.app.core.dao.common.CommonDao;
import colin.app.core.pojo.Homework_User_Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SignupDao {
	@Autowired
	private CommonDao commonDao;

	/**
	 * 
	 * 方法描述：注册用户信息 注意事项：
	 * 
	 * @param homework_user_entity
	 * @return
	 * @Exception 异常对象
	 */
	public boolean signupUserinfo(Homework_User_Entity homework_user_entity) {
			return commonDao.addOneObject(homework_user_entity);
	}
}
