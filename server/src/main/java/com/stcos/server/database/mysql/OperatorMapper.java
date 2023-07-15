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
 * 用于定义数据库中对 Operator 表的操作接口
 *
 * @author masterCheDan
 * @version 1.0
 * @since 2023/5/3 13:21
 */

@Repository
public interface OperatorMapper extends BaseMapper<Operator> {

    /**
     * 根据用户名查找员工对象
     * @param username 员工用户名
     * @return 若找到，则返回相应的 Operator 对象；否则返回 null
     */
    default Operator getByUsernameOperator(String username) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        if (this.selectByMap(map).isEmpty())
            return null;
        else
            return this.selectByMap(map).get(0);
    }

    /**
     * 根据工作编号查找员工对象
     * @param jobNumber 工作编号
     * @return 返回相应的 Operator 对象
     */
    @Select("SELECT * FROM t_operator WHERE job_number = #{jobNumber}")
    @Result(column = "process_instance", property = "processInstanceList", jdbcType = JdbcType.BLOB, typeHandler = ListTypeHandler.class)
    @Result(column = "process_record", property = "processRecordList", jdbcType = JdbcType.BLOB, typeHandler = ListTypeHandler.class)
    Operator selectByJobNumber(String jobNumber);

    /**
     * 通过 Uid 查找员工
     *
     * @param uid 员工 Uid
     * @return 查找到的 Operator 对象
     */
    default Operator getByUidOperator(String uid) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        if (this.selectByMap(map).isEmpty())
            return null;
        else
            return this.selectByMap(map).get(0);
    }

    /**
     * 检查用户名是否存在
     * @param username 员工用户名
     * @return 若用户名存在，则返回 true；否则返回 false
     */
    default boolean existUserName(String username) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        return !this.selectByMap(map).isEmpty();
    }

    /**
     * 根据 QueryWrapper 对象查询对应的 Admin 对象列表
     * @param wrapper QueryWrapper 对象
     * @return 返回相应的 Admin 对象列表
     */
    @Select("select * from t_operator where ${ew.SqlSegment}")
    @Results({
            @Result(column = "uid", property = "uid"),
            @Result(column = "uid", property = "authorities", many = @Many(
                    select = "com.stcos.server.mapper.RoleMapper.getAuthorityListByUid"
            ))
    })
    List<Admin> getList(@Param("ew") QueryWrapper wrapper);

    /**
     * 根据部门查找员工对象列表
     * @param department 部门名称
     * @return 返回相应的 Operator 对象列表
     */
    @Select("SELECT * FROM t_operator WHERE department = #{department}")
    List<Operator> selectByDepartment(String department);
}
