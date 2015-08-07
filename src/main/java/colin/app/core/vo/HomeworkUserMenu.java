package colin.app.core.vo;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by DELL on 2015/7/15.
 */
@Component
public class HomeworkUserMenu {
    private String menu_id;
    private String menu_name;
    private String menu_descripetion;
    private String menu_icon;
    private String menu_url;
    private String menu_order;
    private List<HomeworkUserMenu> homeworkUserMenuList;

    public String getMenu_order() {
        return menu_order;
    }

    public void setMenu_order(String menu_order) {
        this.menu_order = menu_order;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
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

    public List<HomeworkUserMenu> getHomeworkUserMenuList() {
        return homeworkUserMenuList;
    }

    public void setHomeworkUserMenuList(List<HomeworkUserMenu> homeworkUserMenuList) {
        this.homeworkUserMenuList = homeworkUserMenuList;
    }
}
