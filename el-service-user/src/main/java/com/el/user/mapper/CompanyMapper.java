package com.el.user.mapper;

import com.el.framework.model.user.entity.ElCompany;

public interface CompanyMapper {
    int deleteByPrimaryKey(String id);

    int insert(ElCompany record);

    int insertSelective(ElCompany record);

    ElCompany selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ElCompany record);

    int updateByPrimaryKey(ElCompany record);
}