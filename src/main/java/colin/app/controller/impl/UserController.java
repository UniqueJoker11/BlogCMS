package colin.app.controller.impl;

import colin.app.controller.base.HomeworkBaseController;
import colin.app.core.pojo.Homework_Role_Entity;
import colin.app.core.vo.HomeworkUserRoleInfo;
import colin.app.service.UserServcice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/8/6.
 */
@Controller
@RequestMapping("admin")
public class UserController extends HomeworkBaseController {
    @Autowired
    private UserServcice userServcice;

    /**
     * 初始化当前用户信息
     *
     * @return
     */
    @RequestMapping(value = "usermanage.html", method = RequestMethod.GET)
    public String showUserManagePage() {
        //获取用户的信息
        super.getRequestObj().setAttribute("userInfo", userServcice.fetchUserInfo(super.fetchUserInfo().getUser_id()));
        return "usermanage";
    }

    @ResponseBody
    @RequestMapping(value = "fetchUserRole.action", method = RequestMethod.POST)
    public Object fetchUserRoleInfo(@RequestParam(value = "user_id", required = true) String user_id) {
        List<Homework_Role_Entity> role_entityList = userServcice.fetchUserRoleInfo(user_id);
        Map<String, Object> resultMap = new HashMap<>();
        if (role_entityList != null && !role_entityList.isEmpty()) {
            resultMap.put("isSuccess", true);
            resultMap.put("dataList", role_entityList);
        } else {
            resultMap.put("isSuccess", false);
        }
        return resultMap;
    }
    @ResponseBody
    @RequestMapping(value = "/fetchUserRoleTree.action",method = RequestMethod.POST)
    public Object fetchUserRoleTrss(@RequestParam(value = "user_id",required = true)String user_id){
        List<HomeworkUserRoleInfo> userRoleInfoList=this.userServcice.fetchUserRoleInfoTree(user_id);
        Map<String, Object> resultMap = new HashMap<>();
        if (userRoleInfoList != null && !userRoleInfoList.isEmpty()) {
            resultMap.put("isSuccess", true);
            resultMap.put("dataList", userRoleInfoList);
        } else {
            resultMap.put("isSuccess", false);
        }
        return resultMap;
    }
}
