package com.el.user.mapper.user;

import com.el.framework.model.user.entity.ElCompanyUser;

public interface CompanyUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(ElCompanyUser record);

    int insertSelective(ElCompanyUser record);

    ElCompanyUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ElCompanyUser record);

    int updateByPrimaryKey(ElCompanyUser record);
}