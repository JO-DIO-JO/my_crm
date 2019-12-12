package com.shsxt.crm.utils;

import com.shsxt.crm.exceptions.ParamsException;

public class AssertUtil {

    /**
     * 断言工具类：用作各类参数校验
     *  判断 flag 是否为true，为true 抛出异常
     *
     * @param flag
     * @param msg
     */
    public static void isTrue(Boolean flag, String msg) {

        if (flag) {
            throw new ParamsException(msg);
        }

    }

}
