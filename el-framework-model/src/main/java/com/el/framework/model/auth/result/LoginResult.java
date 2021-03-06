package com.el.framework.model.auth.result;

import com.el.framework.response.BaseResult;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author mrd
 * @description 用户登录后的信息返回
 * @date 2020/03/15 10:11
 */
@Data
@ToString
@NoArgsConstructor
public class LoginResult implements BaseResult {

    private String token;

    public LoginResult(String token) {
        this.token = token;
    }
}
