package com.el.gonvern.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.el.framework.code.CommonCode;
import com.el.framework.response.ResponseResult;
import com.el.frameworke.utils.CookieUtil;
import com.el.gonvern.gateway.service.AuthService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author marundong
 * @description
 * @date 2020/4/12 12:56
 */
@Component
public class LoginFilter extends ZuulFilter {

    @Resource
    private AuthService authService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String token = CookieUtil.getTokenFromCookie(request, "uid");
        if (StringUtils.isEmpty(token)) {
            refuse();
            return null;
        }
        long expire = authService.getExpire(token);
        if (expire <= 0) {
            refuse();
            return null;
        }
        String jwtFromHeader = CookieUtil.getJwtFromHeader(request);
        if(StringUtils.isEmpty(jwtFromHeader)){
            refuse();
            return null;
        }
        return null;
    }

    private void refuse() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        currentContext.setSendZuulResponse(false);
        currentContext.setResponseStatusCode(200);
        ResponseResult<Object> responseResult = new ResponseResult<>(CommonCode.UNAUTHENTICATED);
        currentContext.setResponseBody(JSONObject.toJSONString(responseResult));
        currentContext.getResponse().setContentType("application/json; charset=utf-8");
    }
}
