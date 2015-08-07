package colin.app.core.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by DELL on 2015/7/14.
 */
@Table(name="homework_group_authority")
public class Homework_Group_Authority_Entity {
    @Id
    @Column(name = "group_authority_id",nullable = false)
    private String group_authority_id;
    @Column(name = "group_id",nullable = false)
    private String group_id;
    @Column(name = "authority_id",nullable = false)
    private String authority_id;
    @Column(name = "group_authority_type",nullable = false)
    private String group_authority_type;

    public String getGroup_authority_id() {
        return group_authority_id;
    }

    public void setGroup_authority_id(String group_authority_id) {
        this.group_authority_id = group_authority_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getAuthority_id() {
        return authority_id;
    }

    public void setAuthority_id(String authority_id) {
        this.authority_id = authority_id;
    }

    public String getGroup_authority_type() {
        return group_authority_type;
    }

    public void setGroup_authority_type(String group_authority_type) {
        this.group_authority_type = group_authority_type;
    }
}
