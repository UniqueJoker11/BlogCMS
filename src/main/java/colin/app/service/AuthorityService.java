package colin.app.service;

import colin.app.core.dao.decorate.AuthorityDao;
import colin.app.core.pojo.Homework_Authority_Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/8/10.
 */
@Service
public class AuthorityService {

    @Autowired
    private AuthorityDao authorityDao;

    /**
     * 获取用户的权限信息
     * @param roleIds
     * @return
     */
    public List<Homework_Authority_Entity> fetchRoleAuthorityList(List<String> roleIds){
        List<Homework_Authority_Entity> authorityEntityList=new ArrayList<>();
        for (String roleId:roleIds){
            Map<String,Object> authorityParams=new HashMap<>();
            authorityParams.put("role_id",roleId);
            authorityEntityList.addAll(authorityDao.getUserAuthority(authorityParams));
        }
        return authorityEntityList;
    }
}
