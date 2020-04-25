package com.el.user.mapper.user;

import com.el.framework.model.user.entity.ElMenu;

import java.util.List;

public interface MenuMapper {

    int deleteByPrimaryKey(String id);

    List<ElMenu> selectMenuByUserId(String userId);

}