package colin.app.service;

import colin.app.core.dao.decorate.RoleDao;
import colin.app.core.pojo.Homework_Role_Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by DELL on 2015/8/10.
 */
@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 获取所有的系统角色
     * @return
     */
    public List<Homework_Role_Entity> fetchAllRoleList(){
        return roleDao.getAllRoles();
    }
}
