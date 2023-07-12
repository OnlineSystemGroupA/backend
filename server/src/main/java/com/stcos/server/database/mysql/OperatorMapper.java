package com.stcos.server.database.mysql;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.database.mysql.handler.ListTypeHandler;
import com.stcos.server.model.user.Admin;
import com.stcos.server.model.user.Operator;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author masterCheDan
 * @version 1.0
 * @since 2023/5/3 13:21
 */

@Repository
public interface OperatorMapper extends BaseMapper<Operator> {
    default Operator getByUsernameOperator(String username) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        if (this.selectByMap(map).isEmpty())
            return null;
        else
            return this.selectByMap(map).get(0);
    }

    @Select("SELECT * FROM t_operator WHERE job_number = #{jobNumber}")
    @Result(column = "process_instance", property = "processInstanceList", jdbcType = JdbcType.BLOB, typeHandler = ListTypeHandler.class)
    @Result(column = "process_record", property = "processRecordList", jdbcType = JdbcType.BLOB, typeHandler = ListTypeHandler.class)
    Operator selectByJobNumber(String jobNumber);

    /**
     * 通过Uid查找干员
     *
     * @param uid 干员Uid
     * @return 查找到的Operator对象
     */
    default Operator getByUidOperator(String uid) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        if (this.selectByMap(map).isEmpty())
            return null;
        else
            return this.selectByMap(map).get(0);
    }

    default boolean existUserName(String username) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        return !this.selectByMap(map).isEmpty();
    }

    @Select("select * from t_operator where ${ew.SqlSegment}")
    @Results({
            @Result(column = "uid", property = "uid"),
            @Result(column = "uid", property = "authorities", many = @Many(
                    select = "com.stcos.server.mapper.RoleMapper.getAuthorityListByUid"
            ))
    })
    List<Admin> getList(@Param("ew") QueryWrapper wrapper);

    @Select("SELECT * FROM t_operator WHERE department = #{department}")
    List<Operator> selectByDepartment(String department);
}
