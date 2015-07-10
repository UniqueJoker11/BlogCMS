package colin.app.core.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by DELL on 2015/7/10.
 */
@Entity(name = "homework_user")
public class Homework_User_Entity {
    @Id
    @Column(name = "user_id")
    private  String user_id;
    @Column(name="user_name")
    private String user_name;
    @Column(name="user_password")
    private String user_password;
    @Column(name="user_role")
    private String user_role;
    @Column(name="user_callname")
    private String user_callname;
    @Column(name="user_resource_id")
    private String user_resource_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getUser_callname() {
        return user_callname;
    }

    public void setUser_callname(String user_callname) {
        this.user_callname = user_callname;
    }

    public String getUser_resource_id() {
        return user_resource_id;
    }

    public void setUser_resource_id(String user_resource_id) {
        this.user_resource_id = user_resource_id;
    }
}
