package com.el.user.repository.user;

import com.el.framework.model.user.entity.ElUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mrd
 * @description 用户角色
 * @date 2020/03/14 13:10
 */
public interface UserRoleRepository extends JpaRepository<ElUserRole, String> {
}
