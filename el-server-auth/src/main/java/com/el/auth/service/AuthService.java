package com.el.auth.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.el.framework.client.ServiceList;
import com.el.framework.exception.ExceptionCast;
import com.el.framework.model.auth.AuthToken;
import com.el.framework.model.auth.code.AuthCode;
import com.el.framework.model.auth.request.LoginRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author mrd
 * @description 授权服务
 * @date 2020/03/16 15:13
 */
@Service
public class AuthService {

    @Value("${auth.tokenValiditySeconds}")
    int tokenValiditySeconds;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private LoadBalancerClient loadBalancerClient;

    @Resource
    private RestTemplate restTemplate;

    public AuthToken login(LoginRequest loginRequest, String clientId, String clientSecret) {
        //请求spring security申请令牌
        AuthToken authToken = this.applyToken(loginRequest.getUserName(), loginRequest.getPassword(), clientId,
                clientSecret);
        if (authToken == null) {
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_TOKEN_APPLY_FAIL);
        }
        //用户身份令牌
        String accessToken = authToken.getAccessToken();
        //存储到redis中的内容
        String jsonString = JSON.toJSONString(authToken);
        //将令牌存储到redis
        boolean result = this.saveToken(accessToken, jsonString, 60 * 20);
        if (!result) {
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_TOKEN_SAVE_FAIL);
        }
        return authToken;
    }

    private boolean saveToken(String access_token, String content, long tokenValiditySeconds) {
        String key = "user_token:" + access_token;
        stringRedisTemplate.boundValueOps(key).set(content, tokenValiditySeconds, TimeUnit.SECONDS);
        Long expire = stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
        return expire != null && expire > 0;
    }

    private AuthToken applyToken(String username, String password, String clientId, String clientSecret) {
        ServiceInstance serviceInstance = loadBalancerClient.choose(ServiceList.EL_SERVER_AUTH);
        URI uri = serviceInstance.getUri();
        String authUrl = uri + "/auth/oauth/token";
        //定义header
        LinkedMultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        String httpBasic = getHttpBasic(clientId, clientSecret);
        header.add("Authorization", httpBasic);

        //定义body
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("username", username);
        body.add("password", password);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, header);
        //String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables

        //设置restTemplate远程调用时候，对400和401不让报错，正确返回数据
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 400 && response.getRawStatusCode() != 401) {
                    super.handleError(response);
                }
            }
        });

        ResponseEntity<Map> exchange = restTemplate.exchange(authUrl, HttpMethod.POST, httpEntity, Map.class);

        //申请令牌信息
        Map bodyMap = exchange.getBody();
        if (bodyMap == null) {
            return null;
        }
        if (bodyMap.containsKey("error") && bodyMap.get("error") != null) {
            switch (bodyMap.get("error").toString()) {
                case "invalid_grant":
                    ExceptionCast.cast(AuthCode.AUTH_CREDENTIAL_ERROR);
                case "unauthorized":
                    ExceptionCast.cast(AuthCode.AUTH_ACCOUNT_NOT_EXISTS);
                default:
                    return null;
            }
        }

        AuthToken authToken = new AuthToken();
        authToken.setAccessToken((String) bodyMap.get("jti"));//用户身份令牌
        authToken.setRefreshToken((String) bodyMap.get("refresh_token"));//刷新令牌
        authToken.setJwtToken((String) bodyMap.get("access_token"));//jwt令牌
        return authToken;
    }

    //获取httpBasic的串
    private String getHttpBasic(String clientId, String clientSecret) {
        String string = clientId + ":" + clientSecret;
        //将串进行base64编码
        byte[] encode = Base64Utils.encode(string.getBytes());
        return "Basic " + new String(encode);
    }

    /**
     * 获取jwtToken
     *
     * @param token cookieToken
     * @return jwtToken
     */
    public String getUserJwt(String token) {
        String key = "user_token:" + token;
        String value = stringRedisTemplate.opsForValue().get(key);
        AuthToken authToken = JSON.parseObject(value, AuthToken.class);
        return authToken == null ? null : authToken.getJwtToken();
    }

    public boolean deleteToken(String token) {
        String key = "user_token:" + token;
        Boolean delete = stringRedisTemplate.delete(key);
        return delete == null ? false : delete;
    }
}
