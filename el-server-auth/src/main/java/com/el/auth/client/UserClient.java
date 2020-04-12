package com.el.auth.client;

import com.el.framework.client.ServiceList;
import com.el.framework.model.user.result.UserPermissionResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author marundong
 * @description
 * @date 2020/4/12 18:13
 */
@FeignClient(value = ServiceList.EL_SERVICE_USER)
public interface UserClient {
    //根据账号查询用户信息
    @GetMapping("/user/userMng/getUserPermission")
    UserPermissionResult getUserPermissionByUserId(@RequestParam("userId") String userId);
}
