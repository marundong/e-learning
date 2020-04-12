package com.el.auth.controller;

import com.el.api.auth.AuthControllerApi;
import com.el.auth.service.AuthService;
import com.el.framework.code.CommonCode;
import com.el.framework.exception.ExceptionCast;
import com.el.framework.model.auth.AuthToken;
import com.el.framework.model.auth.code.AuthCode;
import com.el.framework.model.auth.request.LoginRequest;
import com.el.framework.model.auth.result.LoginResult;
import com.el.framework.model.auth.result.UserJwtResult;
import com.el.frameworke.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author mrd
 * @description 授权api
 * @date 2020/03/16 15:08
 */
@RestController
@RequestMapping("/")
public class AuthController implements AuthControllerApi {

    public static final String COOKIE_UID = "uid";

    @Value("${auth.clientId}")
    String clientId;
    @Value("${auth.clientSecret}")
    String clientSecret;
    @Value("${auth.cookieDomain}")
    String cookieDomain;
    @Value("${auth.cookieMaxAge}")
    int cookieMaxAge;

    @Resource
    private AuthService authService;

    @Override
    @PostMapping("/login")
    public LoginResult login(@RequestBody LoginRequest loginRequest) {

        AuthToken authToken = authService.login(loginRequest, clientId, clientSecret);
        String accessToken = authToken.getAccessToken();
        saveCookie(accessToken);
        return new LoginResult(accessToken);
    }

    @Override
    @GetMapping("/userJwt")
    public UserJwtResult userJwt() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = CookieUtil.getTokenFromCookie(request, COOKIE_UID);
        String userJwt = authService.getUserJwt(token);
        if(userJwt==null){
            ExceptionCast.cast(CommonCode.FAIL);
        }
        return new UserJwtResult(userJwt);
    }

    @Override
    @PostMapping("/logout")
    public void logout() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = CookieUtil.getTokenFromCookie(request, COOKIE_UID);
        authService.deleteToken(token);
        this.clearCookie(token);
    }

    private void saveCookie(String token) {
        setCookie(token, cookieMaxAge);
    }

    private void clearCookie(String token) {
        setCookie(token, 0);
    }

    private void setCookie(String token, int cookieMaxAge) {
        HttpServletResponse response = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
        assert response != null;
        CookieUtil.addCookie(response, cookieDomain, "/", COOKIE_UID, token, cookieMaxAge, false);
    }
}
