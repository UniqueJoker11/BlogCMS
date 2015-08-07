package colin.app.core.dao.decorate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import colin.app.core.dao.common.CommonDao;
import colin.app.core.dao.rowmapper.Homework_User_Rowmapper;
import colin.app.core.pojo.Homework_User_Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SigninDao {

	@Autowired
	private CommonDao commonDao;
	/**
	 * 
	 * 方法描述：验证用户登录   
	 * 注意事项：    
	 * @param params
	 * @return 
	 * @Exception 异常对象
	 */
	public Map<String,Object> validateUserSignin(Map<String,Object> params){
        Homework_User_Rowmapper rowMapper=new Homework_User_Rowmapper();
		List<Homework_User_Entity> userList=this.commonDao.searchObjectsWithoutPage(Homework_User_Entity.class, params, rowMapper,null);
		//存放返回结果，isExists:用户是否存在,userEntity：假如存在用户在存放入用户信息，msg:反馈的验证信息
		Map<String,Object> validateMap=new HashMap<String,Object>();
		if(userList==null||userList.isEmpty()){
			validateMap.put("isExists",false);
			validateMap.put("msg","查询不到用户信息");
		}else if(userList.size()==1){
			validateMap.put("isExists", true);
			validateMap.put("userEntity",userList.get(0));
		}else{
			validateMap.put("isExists",false);
			validateMap.put("msg","用户信息出现问题，请稍后再试！");
		}
		return validateMap;
	}
}
