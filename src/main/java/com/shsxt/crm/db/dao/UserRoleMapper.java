package com.shsxt.crm.db.dao;

import com.shsxt.base.BaseMapper;
import com.shsxt.crm.vo.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole, Integer> {

    // 获取指定用户的 用户-角色 数量，若大于0则删除（角色分配时用）
    int countUserRoleByUserId(Integer userId);
    int deleteUserRoleByUserId(Integer userId);

    // 获取指定角色的 用户-角色 数量，若大于0则删除（删除角色时用）
    int countUserRoleByRoleId(Integer roleId);
    int deleteUserRoleByRoleId(Integer roleId);
}