package com.el.api.user;

import com.el.framework.code.CommonCode;
import com.el.framework.model.user.request.UserRegisterRequest;
import com.el.framework.model.user.result.UserInfoResult;
import com.el.framework.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author mrd
 * @description 用户管理接口
 * @date 2020/03/14 12:07
 */
@Api(value = "用户管理")
public interface UserManageControllerApi {

    @ApiOperation("根据用户名查询用户信息")
    ResponseResult<UserInfoResult> getUserInfo(String username);

    @ApiOperation("提交用户注册信息")
    ResponseResult<UserInfoResult> userRegister(UserRegisterRequest userRegisterRequest);
}
