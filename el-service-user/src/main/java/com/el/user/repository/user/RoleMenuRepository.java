package com.el.user.repository.user;

import com.el.framework.model.user.entity.ElRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mrd
 * @description 角色权限a
 * @date 2020/03/14 13:10
 */
public interface RoleMenuRepository extends JpaRepository<ElRoleMenu, String> {
}
