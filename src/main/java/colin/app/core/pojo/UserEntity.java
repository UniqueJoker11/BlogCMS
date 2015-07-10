package colin.app.core.pojo;

import javax.persistence.*;

/**
 * Created by joker on 14-9-13.
 */
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @Column(name = "userId")
    private String userId;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="truename")
    private String truename;
    @Column(name="sex")
    private String sex;
    @Column(name="birthday")
    private String birthday;
    @Column(name="dept")
    private String dept;
    @Column(name="position")
    private String position;
    @Column(name="position_desc")
    private String position_desc;
    @Column(name="mobile")
    private String mobile;
    @Column(name="email")
    private String email;
    @Column(name="creator_rname")
    private String creator_rname;
    @Column(name="createdate")
    private String createdate;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition_desc() {
        return position_desc;
    }

    public void setPosition_desc(String position_desc) {
        this.position_desc = position_desc;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreator_rname() {
        return creator_rname;
    }

    public void setCreator_rname(String creator_rname) {
        this.creator_rname = creator_rname;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

}
