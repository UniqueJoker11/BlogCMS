package colin.app.core.dao.rowmapper;

import colin.app.core.pojo.Homework_Menu_Entity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DELL on 2015/7/15.
 */
public class Homework_Menu_Rowmapper implements RowMapper<Homework_Menu_Entity>{
    @Override
    public Homework_Menu_Entity mapRow(ResultSet resultSet, int i) throws SQLException {
        Homework_Menu_Entity homework_menu_entity=new Homework_Menu_Entity();
        homework_menu_entity.setMenu_id(resultSet.getString("menu_id"));
        homework_menu_entity.setMenu_createtime(resultSet.getString("menu_createtime"));
        homework_menu_entity.setMenu_createuser(resultSet.getString("menu_createuser"));
        homework_menu_entity.setMenu_descripetion(resultSet.getString("menu_description"));
        homework_menu_entity.setMenu_icon(resultSet.getString("menu_icon"));
        homework_menu_entity.setMenu_name(resultSet.getString("menu_name"));
        homework_menu_entity.setMenu_order(resultSet.getString("menu_order"));
        homework_menu_entity.setMenu_parent_id(resultSet.getString("menu_parent_id"));
        homework_menu_entity.setMenu_url(resultSet.getString("menu_url"));
        return homework_menu_entity;
    }
}
