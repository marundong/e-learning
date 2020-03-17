package com.el.auth.service;

import com.el.auth.repository.UserRepository;
import com.el.framework.exception.ExceptionCast;
import com.el.framework.model.auth.entity.UserPermission;
import com.el.framework.model.user.code.UserResultCode;
import com.el.framework.model.user.entity.ElUser;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author mrd
 * @description 用户
 * @date 2020/03/16 21:59
 */
@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    public UserPermission getUserByUsername(String username) {
        Optional<ElUser> elUserOptional = userRepository.findByUsername(username);
        if (!elUserOptional.isPresent()) {
            ExceptionCast.cast(UserResultCode.USER_NOT_EXIST);
        }
        ElUser elUser = elUserOptional.get();
        UserPermission userPermission = new UserPermission();
        BeanUtils.copyProperties(elUser, userPermission);
        return userPermission;
    }

}
