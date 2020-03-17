package com.el.framework.model.auth.entity;

import com.el.framework.model.user.entity.ElMenu;
import com.el.framework.model.user.entity.ElUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author mrd
 * @description 用户权限信息
 * @date 2020/03/16 22:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class UserPermission extends ElUser {
    private static final long serialVersionUID = 8494407483354862380L;
    private List<ElMenu> permissions;
    private String companyId;
}
