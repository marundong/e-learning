package com.el.user.controller.auth;

import com.el.api.auth.AuthManageControllerApi;
import com.el.framework.request.PageRequest;
import com.el.framework.response.PageResponseResult;
import com.el.user.service.auth.AuthManageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author marundong
 * @description 权限数据管理
 * @date 2020/4/9 21:10
 */
@RestController
@RequestMapping("/authMng")
@PreAuthorize("hasAuthority('admin')")
public class AuthManageController implements AuthManageControllerApi {

    @Resource
    private AuthManageService authManageService;

    @GetMapping("/clientList")
    @Override
    public PageResponseResult<BaseClientDetails> getClientList(PageRequest pageRequest) {
        return authManageService.getClientList(pageRequest);
    }

    @GetMapping("/get/{clientId}")
    @Override
    public BaseClientDetails getClient(@PathVariable("clientId") String clientId) {
        return authManageService.getClient(clientId);
    }

    @PostMapping("/addClient")
    @Override
    public void addClientDetails(ClientDetails clientDetails) {
        authManageService.addClientDetails(clientDetails);
    }

    @PatchMapping("/updateClient")
    @Override
    public void updateClientDetails(ClientDetails clientDetails) {
        authManageService.updateClientDetails(clientDetails);
    }

    @DeleteMapping("/removeClient/{clientId}")
    @Override
    public void removeClient(@PathVariable("clientId") String clientId) {
        authManageService.removeClientDetails(clientId);
    }

}
