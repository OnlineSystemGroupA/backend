package com.stcos.server.database.mysql;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.model.user.Admin;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定义了对 Admin 实体在 MySQL 数据库中进行操作的一些方法
 * 使用了 MyBatis 的 @Select, @Results, @Result, @Many 等注解进行 SQL 语句的配置
 *
 * @author masterCheDan
 * @version 1.0
 * @since 2023/5/3 13:21
 */
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 根据用户名查找 Admin 实体
     *
     * @param username Admin 实体的用户名
     * @return 如果存在该用户名的 Admin 实体，返回该实体；否则，返回 null
     */
    default Admin getByUsernameAdmin(String username){
        Map<String, Object> map = new HashMap<>();
        map.put("username",username);
        if(this.selectByMap(map).isEmpty())
            return null;
        else
            return this.selectByMap(map).get(0);
    }

    /**
     * 判断用户名是否存在
     *
     * @param username 需要判断是否存在的用户名
     * @return 如果存在该用户名，返回 true；否则，返回 false
     */
    default boolean existUserName(String username) {
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        return !this.selectByMap(map).isEmpty();
    }

    /**
     * 根据 QueryWrapper 条件查询 Admin 实体列表
     *
     * @param wrapper 包含查询条件的 QueryWrapper 对象
     * @return 查询结果的 Admin 实体列表
     */
    @Select("select * from t_admin where ${ew.SqlSegment}")
    @Results({
            @Result(column = "uid",property = "uid"),
            @Result(column = "uid",property = "authorities", many=@Many(
                    select = "com.stcos.server.mapper.RoleMapper.getAuthorityListByUid"
            ))
    })
    List<Admin> getList(@Param("ew") QueryWrapper wrapper);
}
