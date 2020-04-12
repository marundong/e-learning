package com.el.user.service.auth;

import com.el.framework.code.CommonCode;
import com.el.framework.request.PageRequest;
import com.el.framework.response.PageResponseResult;
import com.el.framework.response.PageResult;
import com.el.framework.response.ResponseResult;
import com.el.user.mapper.auth.AuthManageMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author marundong
 * @description 权限相关组件的管理
 * @date 2020/4/9 21:04
 */
@Service
public class AuthManageService {

    @Resource
    private JdbcClientDetailsService clientRegistrationService;

    @Resource
    private AuthManageMapper authManageMapper;

    public PageResponseResult<BaseClientDetails> getClientList(PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<BaseClientDetails> baseClientDetails = authManageMapper.listClientDetails();
        PageInfo<BaseClientDetails> pages = new PageInfo<>(baseClientDetails);
        PageResult<BaseClientDetails> pageResult = new PageResult<>(pages.getList(), pages.getTotal(), pages.getPageSize(),
                pages.getPageNum());

        return new PageResponseResult<>(CommonCode.SUCCESS, pageResult);
    }

    public void addClientDetails(ClientDetails clientDetails) {
        clientRegistrationService.addClientDetails(clientDetails);
    }

    public void updateClientDetails(ClientDetails clientDetails) {
        clientRegistrationService.updateClientDetails(clientDetails);
    }

    public void removeClientDetails(String clientId) {
        clientRegistrationService.removeClientDetails(clientId);
    }

    public BaseClientDetails getClient(String clientId) {
        return authManageMapper.getClientByClientId(clientId);
    }
}
