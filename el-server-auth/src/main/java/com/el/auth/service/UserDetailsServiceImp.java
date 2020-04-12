package com.el.auth.service;

import com.el.auth.client.UserClient;
import com.el.auth.entity.UserJwt;
import com.el.framework.model.auth.entity.UserPermission;
import com.el.framework.model.user.entity.ElMenu;
import com.el.framework.model.user.result.UserPermissionResult;
import com.el.framework.response.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mrd
 * @description
 * @date 2020/03/16 15:38
 */
@Service("UserDetailServiceImp")
public class UserDetailsServiceImp implements UserDetailsService {

    @Resource
    ClientDetailsService clientDetailsService;

    @Resource
    private UserService userService;

    @Resource
    private UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if (authentication == null) {
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
            if (clientDetails != null) {
                //密码
                String clientSecret = clientDetails.getClientSecret();
                return new User(username, clientSecret, AuthorityUtils.commaSeparatedStringToAuthorityList(""));
            }
        }
        UserPermission userPermission = userService.getUserByUsername(username);
        if (userPermission == null) {
            //返回空给spring security表示用户不存在
            return null;
        }

        //取出正确密码（hash值）
        String password = userPermission.getPassword();
        UserPermissionResult userPermissionByUserId =
                userClient.getUserPermissionByUserId(userPermission.getId());
        List<ElMenu> permissions = userPermissionByUserId.getPermissions();
        if (permissions == null) {
            permissions = new ArrayList<>();
        }
        String collect = permissions.stream().map(ElMenu::getCode).collect(Collectors.joining(","));
        UserJwt userDetails = new UserJwt(username,
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(collect));
        userDetails.setId(userPermission.getId());
        userDetails.setUtype(userPermission.getUtype());//用户类型
        userDetails.setCompanyId(userPermission.getCompanyId());//所属企业
        userDetails.setName(userPermission.getName());//用户名称
        userDetails.setUserpic(userPermission.getUserpic());//用户头像
        return userDetails;
    }
}
