package com.el.auth.controller;

import com.el.api.user.AuthControllerApi;
import com.el.auth.service.AuthService;
import com.el.framework.model.auth.AuthToken;
import com.el.framework.model.auth.request.LoginRequest;
import com.el.framework.model.auth.result.LoginResult;
import com.el.frameworke.utils.CookieUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mrd
 * @description 授权api
 * @date 2020/03/16 15:08
 */
@RestController
@RequestMapping("/")
public class AuthController implements AuthControllerApi {

    @Resource
    private AuthService authService;

    @Override
    @PostMapping("/login")
    public LoginResult login(LoginRequest loginRequest) {

        AuthToken authToken = authService.login(loginRequest);
        String accessToken = authToken.getAccessToken();
        saveCookie(accessToken);
        return new LoginResult(accessToken);
    }

    //将令牌存储到cookie
    private void saveCookie(String token){

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        int cookieMaxAge = -1;
        String cookieDomain = "el.com";
        CookieUtil.addCookie(response, cookieDomain,"/","uid",token, cookieMaxAge,false);

    }

    @Override
    public void logout() {

    }
}
