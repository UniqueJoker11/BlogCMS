package colin.app.controller.impl;

import colin.app.common.ChatConfigConnect;
import colin.app.common.CommonConstants;
import colin.app.controller.base.HomeworkBaseController;
import colin.app.core.vo.HomeworkUserInfo;
import colin.app.core.vo.HomeworkUserMenu;
import colin.app.service.IndexService;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/7/15.
 */
@Controller
@Scope(value = "request")
@RequestMapping("admin")
public class IndexController extends HomeworkBaseController {

    @Autowired
    private IndexService indexService;
    @Resource(name = "chatConfigConnect")
    private ChatConfigConnect chatConfigConnect;

    /**
     * 显示管理后台主的页面
     * 加入用户未登录则返回登录页面
     *
     * @return
     */
    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String showRequestPage() throws IOException {
        if (super.userIsLogin()) {
            //加载用户菜单
            String user_id = ((HomeworkUserInfo) super.getSessionObj().getAttribute(CommonConstants.SESSION_USERINFO)).getUser_id();
            List<HomeworkUserMenu> homeworkUserMenuList = indexService.fetchUserMenuService(user_id);
            this.getRequestObj().setAttribute("menuList", homeworkUserMenuList);
            //加载用户的好友列表
            HomeworkUserInfo userInfo=super.fetchUserInfo();
            AbstractXMPPConnection xmppConnection=chatConfigConnect.initXMPPConnection(userInfo.getUser_name(), userInfo.getUser_password());

            return CommonConstants.INDEX;
        } else {
            return CommonConstants.SIGNIN_IN;
        }
    }

    /**
     * 初始化页面的导航信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/menuList.action", method = RequestMethod.POST)
    public Object initUserMenu() {
        String user_id = ((HomeworkUserInfo) super.getSessionObj().getAttribute(CommonConstants.SESSION_USERINFO)).getUser_id();
        List<HomeworkUserMenu> homeworkUserMenuList = indexService.fetchUserMenuService(user_id);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("menuList", homeworkUserMenuList);
        return resultMap;
    }
}
