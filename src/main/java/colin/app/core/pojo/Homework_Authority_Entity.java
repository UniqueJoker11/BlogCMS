package colin.app.core.pojo;
import javax.persistence.*;

/**
 * Created by joker on 15-2-16.
 * 用户权限
 */
@Entity
@Table(name="homework_authority")
public class Homework_Authority_Entity {

/*
 * 权限编号
*/
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
@Column(name ="authority_id")
private String authority_id;

/*
 * 权限名称
*/
@Column(name ="authority_name")
private String authority_name;

/*
 * 权限描述
*/
@Column(name ="authoruty_description")
private String authoruty_description;

    public String getAuthority_id() {
        return authority_id;
    }

    public void setAuthority_id(String authority_id) {
        this.authority_id = authority_id;
    }

    public String getAuthority_name() {
        return authority_name;
    }

    public void setAuthority_name(String authority_name) {
        this.authority_name = authority_name;
    }

    public String getAuthoruty_description() {
        return authoruty_description;
    }

    public void setAuthoruty_description(String authoruty_description) {
        this.authoruty_description = authoruty_description;
    }
}
