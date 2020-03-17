package com.el.user.mapper;

import com.el.framework.model.user.entity.ElUser;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(ElUser record);

    int insertSelective(ElUser record);

    ElUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ElUser record);

    int updateByPrimaryKey(ElUser record);
}