package com.shsxt.crm.enums;

/**
 * 服务状态枚举类
 */
public enum ServeState {

    /**
     * 已创建
     * 已分配
     * 已处理
     * 已反馈
     * 已归档
     */
    CREATED("fw001"),
    ASSIGNED("fw002"),
    PROCEED("fw003"),
    FEEDBACK("fw004"),
    ARCHIVED("fw005");

    private String state;

    ServeState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
