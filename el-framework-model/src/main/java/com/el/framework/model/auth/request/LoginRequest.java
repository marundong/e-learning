package com.el.framework.model.auth.request;

import com.el.framework.request.BaseRequest;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author mrd
 * @description 登录参数
 * @date 2020/03/14 10:55
 */
@Data
public class LoginRequest implements BaseRequest {

    private static final long serialVersionUID = -6891223201153132565L;

    @NotEmpty
    private String userName;

    @NotEmpty
    private String password;

    @NotEmpty
    private String verifyCode;
}
