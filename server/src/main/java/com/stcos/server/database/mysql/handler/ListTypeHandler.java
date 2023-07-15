package com.stcos.server.database.mysql.handler;

import com.stcos.server.util.JSONUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * List 类型处理器，用于将 List 对象转换为数据库字段进行存储和从数据库字段进行读取
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/25 15:04
 */
@SuppressWarnings("rawtypes")
public class ListTypeHandler extends BaseTypeHandler<List> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSONUtil.toJSONString(parameter));
    }

    @Override
    public List getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return JSONUtil.parseObject(rs.getString(columnName), List.class);
    }

    @Override
    public List getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return JSONUtil.parseObject(rs.getString(columnIndex), List.class);
    }

    @Override
    public List getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return JSONUtil.parseObject(cs.getString(columnIndex), List.class);
    }
}
