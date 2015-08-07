package colin.app.core.pojo;

import javax.persistence.*;

/**
 * Created by joker on 15-2-16.
 * POJO类 RightRoleEntity
 */
@Entity
@Table(name = "homework_user_group")
public class Homework_User_Group_Entity {
    @Id
    @Column(name = "user_group_id", nullable = false)
    private String user_group_id;

    @Column(name = "user_id", nullable = false)
    private String user_id;

    @Column(name = "group_id", nullable = false)
    private String group_id;

    public String getUser_group_id() {
        return user_group_id;
    }

    public void setUser_group_id(String user_group_id) {
        this.user_group_id = user_group_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
}
