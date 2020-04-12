package com.el.user.interceptor;

import com.el.framework.code.CommonCode;
import com.el.framework.interceptor.ElResponseBodyAdvice;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author marundong
 * @description
 * @date 2020/4/12 22:06
 */
@RestControllerAdvice
public class UserControllerAdvice extends ElResponseBodyAdvice {
    static {
        builder.put(AccessDeniedException.class, CommonCode.UNAUTHORISE);
    }
}
