package com.el.framework.model.user.result;

import lombok.Data;

import java.util.Date;

/**
 * @author mrd
 * @description 用户信息返回
 * @date 2020/03/14 10:58
 */
@Data
public class UserInfoResult {
    private String id;

    private String username;

    private String name;

    /**
     * 头像
     */
    private String userpic;

    private String utype;

    private Date birthday;

    private String gender;

    private String email;

    private String phone;

    private String qq;

    /**
     * 用户状态
     */
    private String status;
}
