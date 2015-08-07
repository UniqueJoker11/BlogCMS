package colin.app.core.dao.decorate;

import colin.app.core.dao.common.CommonDao;
import colin.app.core.dao.rowmapper.DefaultRowMapper;
import colin.app.core.pojo.Homework_Role_Entity;
import org.apache.commons.collections.map.DefaultedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/8/7.
 */
@Repository
public class RoleDao {
    @Autowired
    private CommonDao commonDao;

    /**
     * 查询所有的角色信息
     * @return
     */
    public List<Homework_Role_Entity> getAllRoles(){
        return commonDao.searchAllObjectsInfo(Homework_Role_Entity.class,null,new DefaultRowMapper<Homework_Role_Entity>(Homework_Role_Entity.class.getName()),null);
    }
}
