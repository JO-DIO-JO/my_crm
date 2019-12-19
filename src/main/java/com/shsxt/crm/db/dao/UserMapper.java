package com.shsxt.crm.db.dao;

import com.shsxt.base.BaseMapper;
import com.shsxt.crm.vo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User, Integer> {

    User queryUserByUsername(String userName);

    List<Map<String, Object>> queryUsersByCustomerManager();
}