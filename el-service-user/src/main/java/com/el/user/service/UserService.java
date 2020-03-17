package com.el.user.service;

import com.el.framework.code.CommonCode;
import com.el.framework.exception.ExceptionCast;
import com.el.framework.model.user.code.UserResultCode;
import com.el.framework.model.user.constants.UserConstants;
import com.el.framework.model.user.entity.ElUser;
import com.el.framework.model.user.request.UserRegisterRequest;
import com.el.framework.model.user.result.UserInfoResult;
import com.el.framework.response.ResponseResult;
import com.el.frameworke.utils.BCryptUtil;
import com.el.user.repository.UserRepository;
import com.el.user.repository.UserRoleRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    @Resource
    UserRepository userRepository;

    /**
     * 用户注册
     *
     * @param userRegister 用户注册信息
     * @return 返回保存后的用户
     */
    public ResponseResult<UserInfoResult> registerUser(@NotNull UserRegisterRequest userRegister) {

        // 用户名校验，手机号校验，邮箱校验
        if (!isRegisterLegal(userRegister)) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }

        ElUser elUser = new ElUser();
        BeanUtils.copyProperties(userRegister, elUser);

        elUser.setCreateTime(new Date());
        elUser.setStatus(UserConstants.USER_STATUS_DEFAUT);
        elUser.setUtype(UserConstants.USER_TYPE_ADMIN);

        elUser.setPassword(BCryptUtil.encode(userRegister.getPassword()));
        ElUser saved = userRepository.save(elUser);
        UserInfoResult result = new UserInfoResult();
        BeanUtils.copyProperties(saved, result);
        return new ResponseResult<>(result);
    }

    /**
     * 校验用户注册信息是否合法
     * 如果用户名、邮箱、手机等已被注册，则抛出对应异常，
     * 如果用户名为空，返回false
     * 如果手机、邮箱都为空，返回false
     *
     * @param userRegister 注册信息
     * @return true表是合法
     */
    private boolean isRegisterLegal(@NotNull UserRegisterRequest userRegister) {

        if (StringUtils.isEmpty(userRegister.getUsername())) {
            return false;
        }

        boolean legal = false;
        Optional<ElUser> byUsername = userRepository.findByUsername(userRegister.getUsername());
        if (byUsername.isPresent()) {
            ExceptionCast.cast(UserResultCode.USER_NAME_EXIST);
        }
        if (StringUtils.isNotEmpty(userRegister.getPhone())) {
            Optional<ElUser> byPhone = userRepository.findByPhone(userRegister.getPhone());
            if (byPhone.isPresent()) {
                ExceptionCast.cast(UserResultCode.USER_PHONE_EXIST);
            }
            legal = true;
        }
        if (StringUtils.isNotEmpty(userRegister.getEmail())) {
            Optional<ElUser> byEmail = userRepository.findByEmail(userRegister.getEmail());
            if (byEmail.isPresent()) {
                ExceptionCast.cast(UserResultCode.USER_EMAIL_EXIST);
            }
            legal = true;
        }
        return legal;
    }

    /**
     * 根据用户名查找用户信息
     *
     * @param username 用户名
     * @return 返回用户信息
     */
    public ResponseResult<UserInfoResult> getUserInfo(String username) {
        Optional<ElUser> elUserOptional = userRepository.findByUsername(username);
        if (!elUserOptional.isPresent()) {
            return new ResponseResult<>(UserResultCode.USER_NOT_EXIST);
        }
        UserInfoResult result = new UserInfoResult();
        BeanUtils.copyProperties(elUserOptional.get(), result);
        return new ResponseResult<>(result);
    }
}
