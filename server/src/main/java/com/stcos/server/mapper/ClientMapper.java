package com.stcos.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stcos.server.entity.user.Client;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author materCheDan
 * @version 1.0
 * @since 2023/5/3 13:21
 */
@Repository
public interface ClientMapper extends BaseMapper<Client> {
    default Client getByUsernameClient(String username){
        Map<String, Object> map = new HashMap<>();
        map.put("username",username);
        if(this.selectByMap(map).isEmpty())
            return null;
        else
            return this.selectByMap(map).get(0);
    }

    /**
     * 增加新用户
     * @param client 待添加用户
     *
     */
    default void addNewUser(Client client){
        this.insert(client);
    }

    default boolean existUserName(String username) {
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        return !this.selectByMap(map).isEmpty();
    }
}
