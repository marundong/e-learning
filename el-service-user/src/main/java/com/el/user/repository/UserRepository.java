package com.el.user.repository;

import com.el.framework.model.user.entity.ElUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author mrd
 * @description 用户管理jpa
 * @date 2020/03/14 13:10
 */
public interface UserRepository extends JpaRepository<ElUser, String> {
    Optional<ElUser> findByUsername(String username);

    Optional<ElUser> findByPhone(String phone);

    Optional<ElUser> findByEmail(String email);

}
