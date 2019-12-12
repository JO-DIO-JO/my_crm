package com.shsxt.crm.db.dao;

import com.shsxt.base.BaseMapper;
import com.shsxt.crm.vo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends BaseMapper<Role, Integer> {

    // 用户功能：角色的分配下拉框的数据来源
    List<Map<String,Object>> queryAllRoles();

    // 获取指定角色名的角色，判断是否存在，保证角色名不能重复
    Role queryRoleByRoleName(@Param("roleName") String roleName);

}