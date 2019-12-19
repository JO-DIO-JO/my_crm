package com.shsxt.crm.query;

import com.shsxt.base.BaseQuery;

public class CustomerServeQuery extends BaseQuery {
    private String type;// 服务类型
    private String customer;// 客户
    private String state;// 服务状态


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
