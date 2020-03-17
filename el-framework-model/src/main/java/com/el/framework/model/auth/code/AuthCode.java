package com.el.framework.model.auth.code;

import com.el.framework.code.ResultCode;
import com.google.common.collect.ImmutableMap;
import lombok.ToString;

/**
 * @author mrd
 * @description 授权处理
 * @date 2020/03/16 17:48
 */
@ToString
public enum AuthCode implements ResultCode {
    AUTH_USERNAME_NONE(false,23001,"请输入账号！"),
    AUTH_PASSWORD_NONE(false,23002,"请输入密码！"),
    AUTH_VERIFY_NONE(false,23003,"请输入验证码！"),
    AUTH_ACCOUNT_NOT_EXISTS(false,23004,"账号不存在！"),
    AUTH_CREDENTIAL_ERROR(false,23005,"用户名或密码错误！"),
    AUTH_LOGIN_ERROR(false,23006,"登陆过程出现异常请尝试重新操作！"),
    AUTH_LOGIN_TOKEN_APPLY_FAIL(false,23007,"申请令牌失败！"),
    AUTH_LOGIN_TOKEN_SAVE_FAIL(false,23008,"存储令牌失败！"),
    AUTH_LOGOUT_FAIL(false,23009,"退出失败！");

    boolean success;
    int code;
    String message;

    AuthCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    private static final ImmutableMap<Integer, AuthCode> CACHE;

    static {
        final ImmutableMap.Builder<Integer, AuthCode> builder = ImmutableMap.builder();
        for (AuthCode commonCode : values()) {
            builder.put(commonCode.code(), commonCode);
        }
        CACHE = builder.build();
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
