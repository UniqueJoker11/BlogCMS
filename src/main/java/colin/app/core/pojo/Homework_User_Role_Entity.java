package colin.app.core.pojo;
import javax.persistence.*;

/**
 * Created by joker on 15-2-16.
 * POJO类 RightColumnEntity
 */
@Entity
@Table(name="homework_user_role")
public class Homework_User_Role_Entity {

    @Id
    @Column(name = "user_role_id",nullable = false)
    private String user_role_id;
    @Column(name = "user_id",nullable = false)
    private String user_id;
    @Column(name = "role_id",nullable = false)
    private String role_id;

    public String getUser_role_id() {
        return user_role_id;
    }

    public void setUser_role_id(String user_role_id) {
        this.user_role_id = user_role_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }
}
