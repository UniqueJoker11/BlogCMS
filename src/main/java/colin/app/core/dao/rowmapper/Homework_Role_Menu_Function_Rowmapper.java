package colin.app.core.dao.rowmapper;

import colin.app.core.pojo.Homework_Role_Menu_Function_Entity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DELL on 2015/7/15.
 */
public class Homework_Role_Menu_Function_Rowmapper implements RowMapper<Homework_Role_Menu_Function_Entity> {
    @Override
    public Homework_Role_Menu_Function_Entity mapRow(ResultSet resultSet, int i) throws SQLException {
        Homework_Role_Menu_Function_Entity homework_role_menu_function_entity = new Homework_Role_Menu_Function_Entity();
        homework_role_menu_function_entity.setRole_menu_function_id(resultSet.getString("role_menu_function_id"));
        homework_role_menu_function_entity.setMenu_id(resultSet.getString("menu_id"));
        homework_role_menu_function_entity.setFunction_id(resultSet.getString("function_id"));
        homework_role_menu_function_entity.setRole_id(resultSet.getString("role_id"));
        return homework_role_menu_function_entity;

    }
}
