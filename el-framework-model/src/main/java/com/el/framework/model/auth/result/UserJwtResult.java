package com.el.framework.model.auth.result;

import com.el.framework.response.BaseResult;
import lombok.Data;

/**
 * @author marundong
 * @description
 * @date 2020/4/12 14:29
 */
@Data
public class UserJwtResult implements BaseResult {
    private String jwt;

    public UserJwtResult() {
    }

    public UserJwtResult(String jwt) {
        this.jwt = jwt;
    }
}
