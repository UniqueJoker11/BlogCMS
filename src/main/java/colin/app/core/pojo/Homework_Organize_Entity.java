package colin.app.core.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by DELL on 2015/7/14.
 */
@Table(name = "homework_organize")
public class Homework_Organize_Entity {
    @Id
    @Column(name = "organize_id",nullable = false)
    private String organize_id;
    @Column(name = "parent_organize_id",nullable = false)
    private String parent_organize_id;
    @Column(name = "organize_name",nullable = false)
    private String organize_name;
    @Column(name = "organize_createtime",nullable = false)
    private String organize_createtime;
    @Column(name = "organize_description",nullable = false)
    private String organize_description;

    public String getOrganize_id() {
        return organize_id;
    }

    public void setOrganize_id(String organize_id) {
        this.organize_id = organize_id;
    }

    public String getParent_organize_id() {
        return parent_organize_id;
    }

    public void setParent_organize_id(String parent_organize_id) {
        this.parent_organize_id = parent_organize_id;
    }

    public String getOrganize_name() {
        return organize_name;
    }

    public void setOrganize_name(String organize_name) {
        this.organize_name = organize_name;
    }

    public String getOrganize_createtime() {
        return organize_createtime;
    }

    public void setOrganize_createtime(String organize_createtime) {
        this.organize_createtime = organize_createtime;
    }

    public String getOrganize_description() {
        return organize_description;
    }

    public void setOrganize_description(String organize_description) {
        this.organize_description = organize_description;
    }
}
