package com.el.user.mapper.user;

import com.el.framework.model.user.entity.ElUserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(ElUserRole record);

    int insertSelective(ElUserRole record);

    ElUserRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ElUserRole record);

    int updateByPrimaryKey(ElUserRole record);
}