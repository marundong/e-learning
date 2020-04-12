package com.el.api.auth;

import com.el.framework.request.PageRequest;
import com.el.framework.response.PageResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author marundong
 * @description
 * @date 2020/4/9 21:09
 */
@Api("权限模块的数据管理")
public interface AuthManageControllerApi {

    @ApiOperation("获取clientDetails列表")
    PageResponseResult getClientList(PageRequest pageRequest);

    @ApiOperation("根据clientId获取clientDetails")
    BaseClientDetails getClient(String clientId);

    @ApiOperation("新增clientDetails")
    void addClientDetails(ClientDetails clientDetails);

    @ApiOperation("更新clientDetails信息")
    void updateClientDetails(ClientDetails clientDetails);

    @DeleteMapping("/removeClient/{clientId}")
    void removeClient(@PathVariable("clientId") String clientId);
}
