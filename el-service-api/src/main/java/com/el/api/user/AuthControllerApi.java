package com.el.api.user;

import com.el.framework.model.auth.request.LoginRequest;
import com.el.framework.model.auth.result.LoginResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author mrd
 * @description 用户认证接口
 * @date 2020/03/15 10:09
 */
@Api(value = "用户认证接口")
public interface AuthControllerApi {

    @ApiOperation("登录")
    LoginResult login(LoginRequest loginRequest);

    @ApiOperation("登出")
    void logout();
}
