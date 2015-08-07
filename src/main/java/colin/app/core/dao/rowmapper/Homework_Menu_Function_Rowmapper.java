package colin.app.core.dao.rowmapper;

import colin.app.core.pojo.Homework_Menu_Function_Entity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DELL on 2015/7/15.
 */
public class Homework_Menu_Function_Rowmapper implements RowMapper<Homework_Menu_Function_Entity>{
    @Override
    public Homework_Menu_Function_Entity mapRow(ResultSet resultSet, int i) throws SQLException {
        Homework_Menu_Function_Entity homework_menu_function_entity=new Homework_Menu_Function_Entity();
        homework_menu_function_entity.setMenu_function_id(resultSet.getString("menu_function_id"));
        homework_menu_function_entity.setMenu_id(resultSet.getString("menu_id"));
        homework_menu_function_entity.setMenu_function_action(resultSet.getString("menu_function_action"));
        homework_menu_function_entity.setMenu_function_description(resultSet.getString("menu_function_description"));
        homework_menu_function_entity.setMenu_function_name(resultSet.getString("menu_function_name"));
        return homework_menu_function_entity;
    }
}
