package com.el.user.mapper.auth;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.List;

/**
 * @author marundong
 * @description
 * @date 2020/4/9 21:18
 */
public interface AuthManageMapper {
    List<BaseClientDetails> listClientDetails();

    BaseClientDetails getClientByClientId(String clientId);
}
