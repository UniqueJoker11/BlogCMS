package colin.app.core.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by DELL on 2015/7/15.
 */
@Table(name = "homework_menu_function")
public class Homework_Menu_Function_Entity {
    @Id
    @Column(name = "menu_function_id",nullable = false)
    private String menu_function_id;
    @Column(name = "menu_function_name",nullable = false)
    private String menu_function_name;
    @Column(name = "menu_function_description",nullable = false)
    private String menu_function_description;
    @Column(name = "menu_function_action",nullable = false)
    private String menu_function_action;
    @Column(name = "menu_id",nullable = false)
    private String menu_id;

    public String getMenu_function_id() {
        return menu_function_id;
    }

    public void setMenu_function_id(String menu_function_id) {
        this.menu_function_id = menu_function_id;
    }

    public String getMenu_function_name() {
        return menu_function_name;
    }

    public void setMenu_function_name(String menu_function_name) {
        this.menu_function_name = menu_function_name;
    }

    public String getMenu_function_description() {
        return menu_function_description;
    }

    public void setMenu_function_description(String menu_function_description) {
        this.menu_function_description = menu_function_description;
    }

    public String getMenu_function_action() {
        return menu_function_action;
    }

    public void setMenu_function_action(String menu_function_action) {
        this.menu_function_action = menu_function_action;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }
}
