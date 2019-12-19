package com.shsxt.crm.service;

import com.shsxt.base.BaseService;
import com.shsxt.crm.enums.ServeState;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.vo.CustomerServe;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerServeService extends BaseService<CustomerServe, Integer> {

    public void saveCustomerServe(CustomerServe customerServe) {
        /**
         * 1.参数校验
         *    客户名非空
         *    服务类型非空
         *    服务请求内容非空
         * 2.字段值设置
         *      state 服务状态值设置
         *      isValid  =1
         *      createDate  updateDate
         * 3.执行添加 判断添加结果
         */
        AssertUtil.isTrue(StringUtils.isBlank(customerServe.getCustomer()), "请指定客户!");
        AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServeType()), "请指定服务类型!");
        AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServiceRequest()), "请指定服务描述信息!");
        customerServe.setIsValid(1);
        customerServe.setCreateDate(new Date());
        customerServe.setUpdateDate(new Date());
        // 设置状态值为已创建
        customerServe.setState(ServeState.CREATED.getState());
        AssertUtil.isTrue(save(customerServe) < 1, "客户服务记录添加失败!");
    }

    public void updateCustomerServe(CustomerServe customerServe) {
        // 执行分配
        AssertUtil.isTrue(StringUtils.isBlank(customerServe.getAssigner()), "请指定分配人!");
        AssertUtil.isTrue(null == customerServe.getId() || null == queryById(customerServe.getId()), "待更新的服务记录不存在!");
        customerServe.setState(ServeState.ASSIGNED.getState());
        customerServe.setAssignTime(new Date());
        customerServe.setUpdateDate(new Date());
        AssertUtil.isTrue(update(customerServe) < 1, "服务记录更新失败!");
    }

}
