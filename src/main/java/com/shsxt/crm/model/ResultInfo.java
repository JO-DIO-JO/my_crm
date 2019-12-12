package com.shsxt.crm.model;

public class ResultInfo<T> {

    private Integer code = 200;
    private String msg = "success";
    private T entity;

    public ResultInfo() {
    }

    public ResultInfo(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
