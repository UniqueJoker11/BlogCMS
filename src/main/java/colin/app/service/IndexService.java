package colin.app.service;

import colin.app.core.dao.decorate.IndexDao;
import colin.app.core.vo.HomeworkUserMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/7/15.
 */
@Service
public class IndexService {

    @Autowired
    protected IndexDao indexDao;

    public List<HomeworkUserMenu> fetchUserMenuService(String user_id){
        Map<String,Object> params=new HashMap<>();
        params.put("user_id",user_id);
        return this.indexDao.searchUserMenuInfo(params);
    }

}
