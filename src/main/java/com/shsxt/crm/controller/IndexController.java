package com.shsxt.crm.controller;

import com.shsxt.base.BaseController;
import com.shsxt.crm.service.PermissionService;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController extends BaseController {

    @Resource
    private UserService userService;

    @Resource
    private PermissionService permissionService;

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "index";
    }

    /**
     * 跳转到管理系统主页面
     *
     * @param request
     * @return
     */
    @RequestMapping("main")
    public String main(HttpServletRequest request) {
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        request.setAttribute("user", userService.queryById(userId));

        // 获取当前用户的所有权限，存入session作用域
        List<String> permissions = permissionService.queryAllModuleAclValueByUserId(userId);
        request.getSession().setAttribute("permissions", permissions);
        return "main";
    }
}
