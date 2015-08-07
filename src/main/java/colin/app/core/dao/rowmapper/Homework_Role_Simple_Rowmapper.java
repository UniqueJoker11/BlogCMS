package colin.app.core.dao.rowmapper;

import colin.app.core.pojo.Homework_Role_Entity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 * Created by DELL on 2015/7/15.
 */
public class Homework_Role_Simple_Rowmapper implements RowMapper<String> {
    @Override
    public String mapRow(ResultSet resultSet, int i) throws SQLException {
        Homework_Role_Entity homework_role_entity = new Homework_Role_Entity();
        return resultSet.getString("role_id");
    }

}
