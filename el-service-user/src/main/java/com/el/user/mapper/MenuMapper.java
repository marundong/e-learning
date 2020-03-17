package com.el.user.mapper;

import com.el.framework.model.user.entity.ElMenu;

public interface MenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(ElMenu record);

    int insertSelective(ElMenu record);

    ElMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ElMenu record);

    int updateByPrimaryKey(ElMenu record);
}