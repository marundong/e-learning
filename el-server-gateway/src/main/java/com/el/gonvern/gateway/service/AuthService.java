package com.el.gonvern.gateway.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author marundong
 * @description
 * @date 2020/4/12 13:13
 */
@Service
public class AuthService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    public long getExpire(String access_token) {
        String key = "user_token:" + access_token;
        Long expire = stringRedisTemplate.getExpire(key);
        return expire == null ? 0 : expire;
    }
}
