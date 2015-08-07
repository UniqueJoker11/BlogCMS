package colin.app.core.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by DELL on 2015/7/14.
 */
@Table(name="homework_role_authority")
public class Homework_Role_Authority_Entity {
    @Id
    @Column(name = "role_authority_id",nullable = false)
    private String role_authority_id;
    @Column(name = "role_id",nullable = false)
    private String role_id;
    @Column(name = "authority_id",nullable = false)
    private String authority_id;
    @Column(name = "authority_type",nullable = false)
    private String authority_type;

    public String getRole_authority_id() {
        return role_authority_id;
    }

    public void setRole_authority_id(String role_authority_id) {
        this.role_authority_id = role_authority_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getAuthority_id() {
        return authority_id;
    }

    public void setAuthority_id(String authority_id) {
        this.authority_id = authority_id;
    }

    public String getAuthority_type() {
        return authority_type;
    }

    public void setAuthority_type(String authority_type) {
        this.authority_type = authority_type;
    }
}
