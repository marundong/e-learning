package com.el.framework.model.auth;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author mrd
 * @description 权限令牌
 * @date 2020/03/16 09:14
 */
@Data
@NoArgsConstructor
@ToString
public class AuthToken implements Serializable {

    private static final long serialVersionUID = 7104207799607623675L;

    String accessToken;

    String refreshToken;

    String jwtToken;
}
