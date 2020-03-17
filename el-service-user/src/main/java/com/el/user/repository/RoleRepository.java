package com.el.user.repository;

import com.el.framework.model.user.entity.ElRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mrd
 * @description 角色管理jpa
 * @date 2020/03/14 13:10
 */
public interface RoleRepository extends JpaRepository<ElRole, String> {
}
