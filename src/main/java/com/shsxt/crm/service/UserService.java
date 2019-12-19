package com.shsxt.crm.service;

import com.shsxt.base.BaseService;
import com.shsxt.crm.db.dao.UserMapper;
import com.shsxt.crm.db.dao.UserRoleMapper;
import com.shsxt.crm.model.UserModel;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.Md5Util;
import com.shsxt.crm.utils.UserIDBase64;
import com.shsxt.crm.vo.User;
import com.shsxt.crm.vo.UserRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserService extends BaseService<User, Integer> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 用户登录
     *  1.参数校验 用户名、用户密码非空
     *  2.根据用户名查询用户
     *  3.不存在，返回结果
     *  4.存在，判断用户密码是否一致（MD5加密）
     *  5.不一致，返回结果
     *  6.一致，返回登录用户信息
     *
     * @param userName  用户名
     * @param userPwd   用户密码
     */
    public UserModel queryUserByUsername(String userName, String userPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户名不能为空！");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd), "用户密码不能为空！");
        User user = userMapper.queryUserByUsername(userName);
        AssertUtil.isTrue(null == user, "用户不存在或已注销！");
        AssertUtil.isTrue(!(user.getUserPwd().equals(Md5Util.encode(userPwd))),"用户密码不正确！");
        return buildUserModelInfo(user);
    }

    /**
     * 将 User 对象 转换成 UserModel
     * @param user
     * @return
     */
    public UserModel buildUserModelInfo(User user) {
        UserModel userModel = new UserModel();
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        return userModel;
    }

    /**
     * 修改用户密码
     *  1. userId 非空 记录必须存在 必须登录
     *  2. 原密码 非空 与数据库一致
     *  3. 新密码 非空 与原密码不一致
     *  4. 确认密码 非空 与新密码一致
     *  5. 新密码MD5加密
     *  6. 执行更新，返回结果
     * @param userId    用户ID
     * @param oldPassword   原密码
     * @param newPassword   新密码
     * @param confirmPassword   确认密码
     */
    public void updateUserPassword(Integer userId, String oldPassword, String newPassword, String confirmPassword) {
        User user = queryById(userId);
        AssertUtil.isTrue(null == userId || null == user, "用户不存在或未登录");
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword), "请输入原密码！");
        AssertUtil.isTrue(!(user.getUserPwd().equals(Md5Util.encode(oldPassword))), "原始密码不正确！");
        AssertUtil.isTrue(StringUtils.isBlank(newPassword), "请输入新密码！");
        AssertUtil.isTrue(StringUtils.isBlank(confirmPassword), "请输入确认密码！");
        AssertUtil.isTrue(newPassword.equals(oldPassword), "新密码不能与原密码一致！");
        AssertUtil.isTrue(!(newPassword.equals(confirmPassword)), "确认密码与新密码不一致！");
        user.setUserPwd(Md5Util.encode(newPassword));
        int row = update(user);
        AssertUtil.isTrue(row < 1, "密码更新失败！");
    }

    /**
     * 1.参数校验
     *     用户名 非空
     *     真实名 非空
     * 2.用户唯一校验
     *     用户名不能重复
     * 3.字段值设置
     *     userPwd=123456
     *     is_valid
     *     createDate
     *     updateDate
     * @param user
     */
    public void saveUser(User user) {
        checkParams(user);
        AssertUtil.isTrue(null != userMapper.queryUserByUsername(user.getUserName()), "该用户已存在");
        user.setUserPwd(Md5Util.encode("123456"));
        user.setIsValid(1);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        AssertUtil.isTrue(save(user) < 1, "用户记录添加失败");

        /**
         * 关联角色   关联中间表 t_user_role
         *  1.需要用户的ID
         *  2.需要分配的角色ID数组
         */
        Integer userId = userMapper.queryUserByUsername(user.getUserName()).getId();
        relationUserRole(userId, user.getRoleIds());
    }

    /**
     * 1.参数校验
     *     用户名 非空
     *     真实名 非空
     *     id 非null  记录存在校验
     * 2.用户唯一校验
     *     用户名不能重复
     * 3.字段值设置
     *     updateDate
     * @param user
     */
    public void updateUser(User user) {
        checkParams(user);
        User tempUser = queryById(user.getId());
        AssertUtil.isTrue(null == user.getId() || null == tempUser, "待更新用户不存在");
        tempUser = userMapper.queryUserByUsername(user.getUserName());
        AssertUtil.isTrue(null != tempUser && !(tempUser.getId().equals(user.getId())), "用户名已存在");
        AssertUtil.isTrue(update(user) < 1, "用户更新失败");
        /**
         * 关联角色   关联中间表 t_user_role
         *  1.需要用户的ID
         *  2.需要分配的角色ID数组
         */
        relationUserRole(user.getId(),user.getRoleIds());
    }

    public void deleteUser(Integer userId) {
        User user = queryById(userId);
        AssertUtil.isTrue(null == userId || null == user, "待删除的用户不存在");
        user.setIsValid(0);
        AssertUtil.isTrue(update(user) < 1, "用户记录删除失败");
    }

    private void relationUserRole(Integer userId, List<Integer> roleIds) {
        /**
         * 用户添加&更新 角色关联实现思路
         *  1. 如果用户角色记录存在
         *        删除原有用户角色记录
         *  2. 如果集合roleIds 记录存在
         *        执行批量添加
         */
        int count = userRoleMapper.countUserRoleByUserId(userId);
        if (count > 0) {
            //用户角色记录存在 -->删除原有用户角色记录
            AssertUtil.isTrue(userRoleMapper.deleteUserRoleByUserId(userId) != count, "用户角色记录关联失败");
        }
        if ( null != roleIds && roleIds.size() > 0) {
            List<UserRole> userRoles = new ArrayList<>();
            roleIds.forEach(rids -> {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(rids);
                userRole.setCreateDate(new Date());
                userRole.setUpdateDate(new Date());
                userRoles.add(userRole);
            });
            AssertUtil.isTrue(userRoleMapper.saveBatch(userRoles) != roleIds.size(), "用户角色记录关联失败");
        }
    }

    private void checkParams(User user) {
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserName()), "用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getTrueName()), "请输入真实名称");
    }

    public List<Map<String, Object>> queryUsersByCustomerManager() {
        return userMapper.queryUsersByCustomerManager();
    }
}
