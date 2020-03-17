package com.el.framework.model.user.code;

import com.el.framework.code.ResultCode;
import lombok.ToString;

/**
 * @description 用户管理返回code
 * @author mrd
 * @date 2020/03/14 10:48
 */
@ToString
public enum UserResultCode implements ResultCode {

    USER_NOT_EXIST(false,20001,"用户不存在！"),
    USER_NAME_EXIST(false,20002,"用户名已被注册"),
    USER_PHONE_EXIST(false,20003,"手机号已被注册"),
    USER_EMAIL_EXIST(false,20004,"邮箱已被注册");

    boolean success;

    int code;

    String message;

    UserResultCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
