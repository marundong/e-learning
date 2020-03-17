package com.el.user.mapper;

import com.el.framework.model.user.entity.ElRoleMenu;

public interface RoleMenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(ElRoleMenu record);

    int insertSelective(ElRoleMenu record);

    ElRoleMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ElRoleMenu record);

    int updateByPrimaryKey(ElRoleMenu record);
}