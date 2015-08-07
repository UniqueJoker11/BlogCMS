package colin.app.core.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by DELL on 2015/7/15.
 */
@Table(name="homework_menu")
public class Homework_Menu_Entity {
    @Id
    @Column(name="menu_id",nullable = false)
    private String menu_id;
    @Column(name="menu_parent_id",nullable = false)
    private String menu_parent_id;
    @Column(name="menu_name",nullable = false)
    private String menu_name;
    @Column(name="menu_descripetion",nullable = false)
    private String menu_descripetion;
    @Column(name="menu_icon",nullable = false)
    private String menu_icon;
    @Column(name="menu_url",nullable = false)
    private String menu_url;
    @Column(name="menu_order",nullable = false)
    private String menu_order;
    @Column(name="menu_createuser",nullable = false)
    private String menu_createuser;
    @Column(name="menu_createtime",nullable = false)
    private String menu_createtime;

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_parent_id() {
        return menu_parent_id;
    }

    public void setMenu_parent_id(String menu_parent_id) {
        this.menu_parent_id = menu_parent_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_descripetion() {
        return menu_descripetion;
    }

    public void setMenu_descripetion(String menu_descripetion) {
        this.menu_descripetion = menu_descripetion;
    }

    public String getMenu_icon() {
        return menu_icon;
    }

    public void setMenu_icon(String menu_icon) {
        this.menu_icon = menu_icon;
    }

    public String getMenu_url() {
        return menu_url;
    }

    public void setMenu_url(String menu_url) {
        this.menu_url = menu_url;
    }

    public String getMenu_order() {
        return menu_order;
    }

    public void setMenu_order(String menu_order) {
        this.menu_order = menu_order;
    }

    public String getMenu_createuser() {
        return menu_createuser;
    }

    public void setMenu_createuser(String menu_createuser) {
        this.menu_createuser = menu_createuser;
    }

    public String getMenu_createtime() {
        return menu_createtime;
    }

    public void setMenu_createtime(String menu_createtime) {
        this.menu_createtime = menu_createtime;
    }
}
