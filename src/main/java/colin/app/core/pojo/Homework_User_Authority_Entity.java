package colin.app.core.pojo;
import javax.persistence.*;

/**
 * Created by joker on 15-2-16.
 * POJO类 UserRightEntity
 */
@Entity
@Table(name="homework_user_authority")
public class Homework_User_Authority_Entity {
    @Id
    @Column(name = "user_authority_id",nullable = false)
    private String user_authority_id;

    @Column(name = "user_id",nullable = false)
    private String user_id;

    @Column(name = "authority_id",nullable = false)
    private String authority_id;

    @Column(name = "authority_description",nullable = false)
    private String authority_description;

    public String getUser_authority_id() {
        return user_authority_id;
    }

    public void setUser_authority_id(String user_authority_id) {
        this.user_authority_id = user_authority_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAuthority_id() {
        return authority_id;
    }

    public void setAuthority_id(String authority_id) {
        this.authority_id = authority_id;
    }

    public String getAuthority_description() {
        return authority_description;
    }

    public void setAuthority_description(String authority_description) {
        this.authority_description = authority_description;
    }
}
