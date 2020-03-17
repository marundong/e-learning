package com.el.framework.exception;

import com.el.framework.code.ResultCode;

/**
 * 抛出异常封装
 **/
public class ExceptionCast {

    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }
}
