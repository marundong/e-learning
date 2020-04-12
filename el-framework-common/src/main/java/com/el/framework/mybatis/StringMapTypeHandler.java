package com.el.framework.mybatis;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author marundong
 * @description
 * @date 2020/4/11 16:32
 */
public class StringMapTypeHandler extends BaseTypeHandler<Map> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Map parameter, JdbcType jdbcType) throws SQLException {
        String string = JSONObject.toJSONString(parameter);
        ps.setString(i, string);
    }

    @Override
    public Map getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        if (rs.wasNull())
            return null;
        return JSONObject.parseObject(str, LinkedHashMap.class);
    }

    @Override
    public Map getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        if (rs.wasNull())
            return null;

        return JSONObject.parseObject(str, LinkedHashMap.class);
    }

    @Override
    public Map getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String str = cs.getString(columnIndex);
        if (cs.wasNull())
            return null;
        return JSONObject.parseObject(str, LinkedHashMap.class);
    }
}
