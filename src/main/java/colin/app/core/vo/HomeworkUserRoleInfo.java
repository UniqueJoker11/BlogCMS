package colin.app.core.vo;

import java.util.List;

/**
 * Created by DELL on 2015/8/7.
 * 用户的角色信息
 */
public class HomeworkUserRoleInfo {
    private String roleId;
    private String parentRoleId;
    private String name;
    private String roleDescription;
    private boolean checked;
    private List<HomeworkUserRoleInfo> childrenList;


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getParentRoleId() {
        return parentRoleId;
    }

    public void setParentRoleId(String parentRoleId) {
        this.parentRoleId = parentRoleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public List<HomeworkUserRoleInfo> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<HomeworkUserRoleInfo> childrenList) {
        this.childrenList = childrenList;
    }
}
