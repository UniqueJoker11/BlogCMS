package colin.app.core.dao.decorate;

import colin.app.core.dao.common.CommonDao;
import colin.app.core.dao.rowmapper.DefaultRowMapper;
import colin.app.core.dao.rowmapper.Homework_Role_Simple_Rowmapper;
import colin.app.core.dao.rowmapper.Homework_User_Role_Simple_Rowmapper;
import colin.app.core.pojo.Homework_Role_Entity;
import colin.app.core.pojo.Homework_User_Entity;
import colin.app.core.pojo.Homework_User_Role_Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by DELL on 2015/8/6.
 */
@Repository
public class UserDao {
    @Autowired
    private CommonDao commonDao;

    /**
     * 根据用户的角色来查询:根据当前用户查询当前用户角色及其子角色，然后将对应的每个子角色的用户查询出来
     *
     * @param params
     * @return
     */
    public List<Homework_User_Entity> fetchUserList(Map<String, Object> params) {
        //根据用户id查询对应的角色id
        List<Homework_User_Role_Entity> user_role_entityList = commonDao.searchObjectsWithoutPage(Homework_User_Role_Entity.class, params, new DefaultRowMapper<Homework_User_Role_Entity>(Homework_User_Role_Entity.class.getName()), null);
        //根据角色id查询对应的角色及其子角色
        Set<String> role_entitySet = new HashSet<>();
        for (Homework_User_Role_Entity role_entity : user_role_entityList) {
            Map<String, Object> searchRoleParams = new HashMap<>();
            searchRoleParams.put("role_id", role_entity.getRole_id());
            NamedParameterJdbcTemplate parameterJdbcTemplate=commonDao.getNamedParameterJdbcTemplate();
            List<String> role_entityList= parameterJdbcTemplate.query("select * from homework_role where FIND_IN_SET(role_id, getChildLst(:role_id))",searchRoleParams, new Homework_Role_Simple_Rowmapper());
            role_entitySet.addAll(role_entityList);
        }
        //根据所有的用户角色查询当前角色的用户
        Set<String> homework_user_role_entities=new HashSet<>();
        for(String role_id:role_entitySet){
            Map<String, Object> searchUserParams = new HashMap<>();
            searchUserParams.put("role_id",role_id);
            List<String> user_role_entityListSub=commonDao.searchAllObjectsInfo(Homework_User_Role_Entity.class, searchUserParams, new Homework_User_Role_Simple_Rowmapper(), null);
            homework_user_role_entities.addAll(user_role_entityListSub);

        }
        //根绝userid来查询用户的内容
        List<Homework_User_Entity> user_entityList=new ArrayList<>();
        for(String user_id:homework_user_role_entities){
            user_entityList.add(commonDao.searchObjectByIdWithoutRowmapper(Homework_User_Entity.class,user_id));
        }
        return user_entityList;
    }

    /**
     * 根绝userid来查询出user_role的信息
     * @param userParams
     * @return
     */
    public List<Homework_Role_Entity> fetchUserRoleInfos(Map<String,Object> userParams){
       //获取当前用户对应的所有角色
        List<Homework_User_Role_Entity> user_role_entityList=commonDao.searchAllObjectsInfo(Homework_User_Role_Entity.class,userParams,new DefaultRowMapper<Homework_User_Role_Entity>(Homework_User_Role_Entity.class.getName()),null);
        //根绝角色id，返回对应的角色信息
        List<Homework_Role_Entity> role_entityList=new ArrayList<>();
        for(Homework_User_Role_Entity user_role_entity:user_role_entityList){
            role_entityList.add(commonDao.searchObjectById(Homework_Role_Entity.class,user_role_entity.getRole_id(),new DefaultRowMapper<Homework_Role_Entity>(Homework_Role_Entity.class.getName())));
        }
        return role_entityList;
    }
}
