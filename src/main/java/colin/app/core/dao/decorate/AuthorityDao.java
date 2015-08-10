package colin.app.core.dao.decorate;

import colin.app.core.dao.common.CommonDao;
import colin.app.core.dao.rowmapper.DefaultRowMapper;
import colin.app.core.pojo.Homework_Authority_Entity;
import colin.app.core.pojo.Homework_Role_Authority_Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/8/10.
 */
@Repository
public class AuthorityDao {
    @Autowired
    private CommonDao commonDao;

    /**
     * 根绝用户的角色加载用户的权限,
     * 根据传入过来的role_id来获取authority内容
     */
    public List<Homework_Authority_Entity> getUserAuthority(Map<String, Object> authorityParams) {
        List<Homework_Role_Authority_Entity> role_authority_entities = commonDao.searchAllObjectsInfo(Homework_Role_Authority_Entity.class, authorityParams, new DefaultRowMapper<Homework_Role_Authority_Entity>(Homework_Role_Authority_Entity.class.getName()), null);
        //根据所有的authority_id来获取所有的authority信息
        List<Homework_Authority_Entity> authorityEntityList = new ArrayList<>();
        for (Homework_Role_Authority_Entity role_authority_entity : role_authority_entities) {
            Homework_Authority_Entity authority_entity = commonDao.searchObjectById(Homework_Authority_Entity.class, role_authority_entity.getAuthority_id(), new DefaultRowMapper<Homework_Authority_Entity>(Homework_Authority_Entity.class.getName()));
            authorityEntityList.add(authority_entity);
        }
        return authorityEntityList;
    }
}
