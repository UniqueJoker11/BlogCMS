package colin.app.core.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by DELL on 2015/7/14.
 * 用户组
 */
@Entity
@Table(name = "homework_group")
public class Homework_Group_Entity {
    @Id
    @Column(name = "group_id", nullable = false)
    private String group_id;
    @Column(name = "parent_group_id", nullable = false)
    private String parent_group_id;
    @Column(name = "group_name", nullable = false)
    private String group_name;
    @Column(name = "group_description", nullable = false)
    private String group_description;
    @Column(name = "group_createtime", nullable = false)
    private String group_createtime;

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getParent_group_id() {
        return parent_group_id;
    }

    public void setParent_group_id(String parent_group_id) {
        this.parent_group_id = parent_group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_description() {
        return group_description;
    }

    public void setGroup_description(String group_description) {
        this.group_description = group_description;
    }

    public String getGroup_createtime() {
        return group_createtime;
    }

    public void setGroup_createtime(String group_createtime) {
        this.group_createtime = group_createtime;
    }
}
