package colin.app.service;

import colin.app.core.dao.decorate.RoleDao;
import colin.app.core.dao.decorate.UserDao;
import colin.app.core.pojo.Homework_Role_Entity;
import colin.app.core.pojo.Homework_User_Entity;
import colin.app.core.vo.HomeworkSimpleUserInfo;
import colin.app.core.vo.HomeworkUserInfo;
import colin.app.core.vo.HomeworkUserRoleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/8/6.
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service
public class UserServcice {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    /**
     *获取用户的详细信息
     */
    public HomeworkUserInfo fetchUserDetailInfo(String user_id){
        Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("user_id", user_id);
        Homework_User_Entity homework_user_entity=userDao.fetchUserDetailInfo(user_id);
        return this.transferUserInfo(homework_user_entity);
    }

    /**
     * 获取用户的请求
     */
    public List<HomeworkSimpleUserInfo> fetchUserInfo(String user_id) {
        Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("user_id", user_id);
        return this.transferUserInfo(userDao.fetchUserList(searchParams));
    }

    /**
     * 根据用户的id来获取当前用户的角色信息
     *
     * @param user_id
     * @return
     */
    public List<Homework_Role_Entity> fetchUserRoleInfo(String user_id) {
        Map<String, Object> userParams = new HashMap<>();
        userParams.put("user_id", user_id);
        //根据用户user_id来查询出相应的user_role信息
        return userDao.fetchUserRoleInfos(userParams);
    }

    /**
     * 根据用户id来获取用户的角色树
     *
     * @param user_id
     * @return
     */
    public List<HomeworkUserRoleInfo> fetchUserRoleInfoTree(String user_id) {
        List<Homework_Role_Entity> role_entityList = roleDao.getAllRoles();
        Map<String, Object> userParams = new HashMap<>();
        userParams.put("user_id", user_id);
        //根据用户user_id来查询出相应的user_role信息
        List<Homework_Role_Entity> user_roleList = userDao.fetchUserRoleInfos(userParams);
        //合成全新的返回值
        List<HomeworkUserRoleInfo> userRoleParInfoList = new ArrayList<>();
        List<HomeworkUserRoleInfo> userRoleSubInfoList = new ArrayList<>();
        for (Homework_Role_Entity role_entity : role_entityList) {
            HomeworkUserRoleInfo userRoleInfo = new HomeworkUserRoleInfo();
            userRoleInfo.setRoleId(role_entity.getRole_id());
            userRoleInfo.setParentRoleId(role_entity.getParent_role_id());
            userRoleInfo.setName(role_entity.getRole_name());
            userRoleInfo.setRoleDescription(role_entity.getRole_description());
            if (this.isExistsInUserRoles(role_entity, user_roleList)) {
                userRoleInfo.setChecked(true);
            } else {
                userRoleInfo.setChecked(false);
            }
            if(role_entity.getParent_role_id().equals("0")){
                userRoleParInfoList.add(userRoleInfo);
            }else{
                userRoleSubInfoList.add(userRoleInfo);
            }

        }
        for(HomeworkUserRoleInfo roleInfo:userRoleParInfoList){
            roleInfo.setChildrenList(this.sortUserRoleInfoList(roleInfo.getRoleId(),userRoleSubInfoList));
        }
        return userRoleParInfoList;
    }

    /**
     * 判断当前的角色是否属于用户角色
     *
     * @param role_entity
     * @param user_roleList
     * @return
     */
    private boolean isExistsInUserRoles(Homework_Role_Entity role_entity, List<Homework_Role_Entity> user_roleList) {
        boolean result = false;
        for (Homework_Role_Entity current_role : user_roleList) {
            if (role_entity.getRole_id().equals(current_role.getRole_id())) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * 根绝传入的父id来查询出相应的子节点
     * @param targetId
     * @param sourceList
     * @return
     */
    public List<HomeworkUserRoleInfo> sortUserRoleInfoList(String targetId,List<HomeworkUserRoleInfo> sourceList){
        List<HomeworkUserRoleInfo> nodeList=new ArrayList<>();
        for(HomeworkUserRoleInfo roleInfo:sourceList){
            if(targetId.equals(roleInfo.getParentRoleId())){
                List<HomeworkUserRoleInfo> subList=sortUserRoleInfoList(roleInfo.getRoleId(),sourceList);
                if(subList!=null&&!subList.isEmpty()){
                    roleInfo.setChildrenList(subList);
                }
                nodeList.add(roleInfo);
            }
        }
        return nodeList;
    }
    private List<HomeworkSimpleUserInfo> transferUserInfo(List<Homework_User_Entity> user_entityList){
        List<HomeworkSimpleUserInfo> simpleUserInfoList=new ArrayList<>();
        for(Homework_User_Entity homework_user_entity:user_entityList){
            HomeworkSimpleUserInfo simpleUserInfo=new HomeworkSimpleUserInfo();
            simpleUserInfo.setId(homework_user_entity.getUser_id());
            simpleUserInfo.setName(homework_user_entity.getUser_name());
            simpleUserInfoList.add(simpleUserInfo);
        }
        return simpleUserInfoList;
    }

    /**
     * 转换用户信息
     * @param homework_user_entity
     * @return
     */
    public HomeworkUserInfo transferUserInfo(Homework_User_Entity homework_user_entity){
        HomeworkUserInfo homeworkUserInfo=new HomeworkUserInfo();
        homeworkUserInfo.setUser_id(homework_user_entity.getUser_id());
        homeworkUserInfo.setUser_organize_id(homework_user_entity.getUser_organize_id());
        homeworkUserInfo.setUser_name(homework_user_entity.getUser_name());
        homeworkUserInfo.setUser_logintime(homework_user_entity.getUser_logintime());
        homeworkUserInfo.setUser_callname(homework_user_entity.getUser_callname());
        homeworkUserInfo.setUser_createtime(homework_user_entity.getUser_createtime());
        homeworkUserInfo.setUser_email(homework_user_entity.getUser_email());
        homeworkUserInfo.setUser_password(homework_user_entity.getUser_password());
        homeworkUserInfo.setUser_phone(homework_user_entity.getUser_phone());
        return homeworkUserInfo;
    }
}
