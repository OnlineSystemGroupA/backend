package com.stcos.server.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.pojo.po.Admin;
import com.stcos.server.pojo.po.Operator;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author
 * @version 1.0
 * @since 2023/5/3 13:21
 */

@Repository
public interface OperatorMapper extends BaseMapper<Operator> {
    default Operator getByUsernameOperator(String username){
        Map<String, Object> map = new HashMap<>();
        map.put("username",username);
        if(this.selectByMap(map).isEmpty())
            return null;
        else
            return this.selectByMap(map).get(0);
    }

    default boolean existUserName(String username) {
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        return !this.selectByMap(map).isEmpty();
    }

    @Select("select * from user where ${ew.SqlSegment}")
    @Results({
            @Result(column = "uid",property = "uid"),
            @Result(column = "uid",property = "authorities", many=@Many(
                    select = "com.stcos.server.mapper.RoleMapper.getListByUserId"
            ))
    })
    List<Admin> getList(@Param("ew") QueryWrapper wrapper);
}
