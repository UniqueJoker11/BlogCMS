package colin.app.controller.impl;

import colin.app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DELL on 2015/8/10.
 */
@Controller
public class RoleManageController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/rolemanage.html", method = RequestMethod.GET)
    public String showRoleManage() {
        return "rolemanage";
    }

    @ResponseBody
    @RequestMapping(value = "/fetchAllRoleList.action", method = RequestMethod.POST)
    public Object fetchAllRole() {
      return this.roleService.fetchAllRoleList();
    }

}
