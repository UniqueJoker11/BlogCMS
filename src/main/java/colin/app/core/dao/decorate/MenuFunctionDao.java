package colin.app.core.dao.decorate;

import colin.app.core.dao.common.CommonDao;
import colin.app.core.pojo.Homework_Menu_Function_Entity;
import colin.app.core.pojo.Homework_Role_Menu_Function_Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by DELL on 2015/7/15.
 */
@Repository
public class MenuFunctionDao {
    @Autowired
    private CommonDao commonDao;
}
