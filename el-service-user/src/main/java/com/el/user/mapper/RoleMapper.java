package com.el.user.mapper;

import com.el.framework.model.user.entity.ElRole;

public interface RoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(ElRole record);

    int insertSelective(ElRole record);

    ElRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ElRole record);

    int updateByPrimaryKey(ElRole record);
}