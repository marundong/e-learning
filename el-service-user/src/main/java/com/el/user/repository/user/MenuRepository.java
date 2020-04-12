package com.el.user.repository.user;

import com.el.framework.model.user.entity.ElMenu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mrd
 * @description 菜单管理jpa
 * @date 2020/03/14 13:10
 */
public interface MenuRepository extends JpaRepository<ElMenu, String> {
}
