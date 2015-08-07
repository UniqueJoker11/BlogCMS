package colin.app.core.dao.rowmapper;


import colin.app.core.pojo.Homework_User_Role_Entity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DELL on 2015/7/15.
 */
public class Homework_User_Role_Simple_Rowmapper implements RowMapper<String> {
    @Override
    public String mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getString("user_id");
    }
}
