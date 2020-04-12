package com.el.framework.model.user.result;

import com.el.framework.model.user.entity.ElMenu;
import com.el.framework.response.BaseResult;
import lombok.Data;

import java.util.List;

/**
 * @author marundong
 * @description
 * @date 2020/4/12 17:56
 */
@Data
public class UserPermissionResult {

    private List<ElMenu> permissions;

    private String companyId;
}
