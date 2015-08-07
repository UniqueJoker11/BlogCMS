package colin.app.service;

import colin.app.core.dao.decorate.SigninDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by DELL on 2015/7/14.
 */
@Service
public class SigninService {
    @Autowired
    private SigninDao signinDao;

    /**
     *
     * 方法描述：验证用户的信息是否正确
     * 注意事项：
     * @param params
     * @return
     * @Exception 异常对象
     */
    public Map<String, Object> validateUserSigninService(
            Map<String, Object> params) {
        Map<String, Object> signinParams = signinDao.validateUserSignin(params);
        return signinParams;
    }
    /**
     *
     * 方法描述：验证用户名是否存在
     * 注意事项：
     * @param params
     * @return
     * @Exception 异常对象
     */
    public Boolean validateUsernameService(Map<String,Object> params){
        Map<String,Object> signinParams=signinDao.validateUserSignin(params);
        return (Boolean)signinParams.get("isExists");
    }
}
