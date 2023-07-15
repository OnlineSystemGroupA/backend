package com.stcos.server.database.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.model.user.Client;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义了对 Client 实体在 MySQL 数据库中进行操作的一些方法
 * 使用了 MyBatis 的 BaseMapper 进行基础 SQL 语句的配置，同时定义了一些 Client 实体特有的数据库操作方法
 *
 * @author materCheDan
 * @version 1.0
 * @since 2023/5/3 13:21
 */
@Repository
public interface ClientMapper extends BaseMapper<Client> {

    /**
     * 根据用户名查找 Client 实体
     *
     * @param username Client 实体的用户名
     * @return 如果存在该用户名的 Client 实体，返回该实体；否则，返回 null
     */
    default Client getByUsernameClient(String username){
        Map<String, Object> map = new HashMap<>();
        map.put("username",username);
        if(this.selectByMap(map).isEmpty())
            return null;
        else
            return this.selectByMap(map).get(0);
    }

    /**
     * 根据 uid 查找 Client 实体
     *
     * @param uid Client 实体的 uid
     * @return 如果存在该 uid 的 Client 实体，返回该实体；否则，返回 null
     */
    default Client getByUidClient(String uid){
        Map<String, Object> map = new HashMap<>();
        map.put("uid",uid);
        if(this.selectByMap(map).isEmpty())
            return null;
        else
            return this.selectByMap(map).get(0);
    }

    /**
     * 增加新用户
     *
     * @param client 需要添加的 Client 实体
     */
    default void addNewUser(Client client){
        this.insert(client);
    }

    /**
     * 判断用户名是否存在
     *
     * @param username 需要判断是否存在的用户名
     * @return 如果存在该用户名，返回 true；否则，返回 false
     */
    default boolean existUsername(String username) {
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        return !this.selectByMap(map).isEmpty();
    }
}
