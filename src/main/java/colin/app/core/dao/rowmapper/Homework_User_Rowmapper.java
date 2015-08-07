package colin.app.core.dao.rowmapper;


import colin.app.core.pojo.Homework_User_Entity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DELL on 2015/7/14.
 */
public class Homework_User_Rowmapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Homework_User_Entity user_entity=new Homework_User_Entity();
        user_entity.setUser_id(resultSet.getString("user_id"));
        user_entity.setUser_callname(resultSet.getString("user_callname"));
        user_entity.setUser_createtime(resultSet.getString("user_createtime"));
        user_entity.setUser_email(resultSet.getString("user_email"));
        user_entity.setUser_logintime(resultSet.getString("user_logintime"));
        user_entity.setUser_name(resultSet.getString("user_name"));
        user_entity.setUser_organize_id(resultSet.getString("user_organize_id"));
        user_entity.setUser_password(resultSet.getString("user_password"));
        user_entity.setUser_phone(resultSet.getString("user_phone"));
        return user_entity;
    }
}
