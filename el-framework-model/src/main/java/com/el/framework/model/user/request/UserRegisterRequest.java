package com.el.framework.model.user.request;

import com.el.framework.request.BaseRequest;
import com.el.framework.validate.Phone;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author mrd
 * @description 用户注册信息
 * @date 2020/03/14 11:05
 */
@Data
public class UserRegisterRequest implements BaseRequest {

    private static final long serialVersionUID = -3765900551082272493L;

    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    private String password;

    private String name;

    @Email(message = "邮箱地址错误")
    private String email;

    @Phone
    private String phone;

}
