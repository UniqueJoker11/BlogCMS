package colin.app.core.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by DELL on 2015/7/14.
 */
@Table(name = "homework_group_role")
public class Homework_Group_Role_Entity {
    @Id
    @Column(name = "group_role_id",nullable = false)
    private String group_role_id;
    @Column(name = "group_id",nullable = false)
    private String group_id;
    @Column(name = "role_id",nullable = false)
    private String role_id;

    public String getGroup_role_id() {
        return group_role_id;
    }

    public void setGroup_role_id(String group_role_id) {
        this.group_role_id = group_role_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

}
