package com.el.user.controller.user;

import com.el.api.user.UserManageControllerApi;
import com.el.framework.model.user.request.UserRegisterRequest;
import com.el.framework.model.user.result.UserInfoResult;
import com.el.framework.model.user.result.UserPermissionResult;
import com.el.framework.response.ResponseResult;
import com.el.user.service.user.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;

/**
 * @author mrd
 * @description 用户管理接口实现
 * @date 2020/03/14 12:32
 */
@RestController
@RequestMapping("/userMng")
public class UserManageController implements UserManageControllerApi {

    @Resource
    UserService userService;

    @Override
    @GetMapping("/getUserInfo")
    public ResponseResult<UserInfoResult> getUserInfo(@NotEmpty @RequestParam(value = "username") String username) {
        return userService.getUserInfo(username);
    }

    @Override
    @PostMapping("/register")
    public ResponseResult<UserInfoResult> userRegister(@RequestBody @Validated UserRegisterRequest userRegisterRequest) {
        return userService.registerUser(userRegisterRequest);
    }

    @Override
    @GetMapping("/getUserPermission")
    public UserPermissionResult getUserPermissionByUserId(@RequestParam("userId") String userId){
        return userService.getUserPermission(userId);
    }
}
