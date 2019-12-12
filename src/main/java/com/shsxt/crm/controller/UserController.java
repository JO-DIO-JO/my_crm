package com.shsxt.crm.controller;

import com.shsxt.base.BaseController;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.model.UserModel;
import com.shsxt.crm.query.UserQuery;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import com.shsxt.crm.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping("user")
@Controller
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    /**
     * 测试DAO层是否配置成功
     * 根据ID查询用户对象
     *
     * @param userId
     * @return
     */
    @RequestMapping("test")
    @ResponseBody
    public User test(Integer userId) {
        User user = userService.queryById(userId);
        return user;
    }

    /**
     * 用户登录
     *
     * @param userName
     * @param userPwd
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public ResultInfo login(String userName, String userPwd) {
        ResultInfo<UserModel> resultInfo = new ResultInfo<>();
        UserModel userModel = userService.queryUserByUsername(userName, userPwd);
        resultInfo.setEntity(userModel);
        return resultInfo;
    }

    /**
     * 修改用户密码
     *
     * @param request
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @return
     */
    @RequestMapping("updateUserPassword")
    @ResponseBody
    public ResultInfo updateUserPassword(HttpServletRequest request, String oldPassword, String newPassword, String confirmPassword) {
        ResultInfo<Object> resultInfo = new ResultInfo<>();
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        userService.updateUserPassword(userId, oldPassword, newPassword, confirmPassword);
        resultInfo.setMsg("密码修改成功！");
        return resultInfo;
    }

    @RequestMapping("index")
    public String index() {
        return "user";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryUsersByParams(UserQuery userQuery,
                                                  @RequestParam(defaultValue = "1") Integer page,
                                                  @RequestParam(defaultValue = "10") Integer rows) {
        userQuery.setPageNum(page);
        userQuery.setPageSize(rows);
        return userService.queryByParamsForDataGrid(userQuery);
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(User user) {
        userService.saveUser(user);
        return success("用户数据添加成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(User user) {
        userService.updateUser(user);
        return success("用户数据更新成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(@RequestParam(name = "id") Integer userId) {
        userService.deleteUser(userId);
        return success("用户数据删除成功");
    }
}
