package colin.app.core.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by DELL on 2015/7/15.
 */
@Table(name = "homework_role_menu_function")
public class Homework_Role_Menu_Function_Entity {
    @Id
    @Column(name = "role_menu_function_id",nullable = false)
    private String role_menu_function_id;
    @Column(name = "function_id",nullable = false)
    private String function_id;
    @Column(name = "menu_id",nullable = false)
    private String menu_id;
    @Column(name = "role_id",nullable = false)
    private String role_id;

    public String getRole_menu_function_id() {
        return role_menu_function_id;
    }

    public void setRole_menu_function_id(String role_menu_function_id) {
        this.role_menu_function_id = role_menu_function_id;
    }

    public String getFunction_id() {
        return function_id;
    }

    public void setFunction_id(String function_id) {
        this.function_id = function_id;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }
}
