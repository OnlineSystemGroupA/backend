package com.stcos.server.database.impl;

import com.stcos.server.database.UserRepo;
import com.stcos.server.mapper.UserMapper;
import com.stcos.server.pojo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/4/3 20:52
 */

@Component
public class UserRepoImp implements UserRepo {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserByName(String username){
        Map<String, Object> map = new HashMap<>();
        map.put("username",username);
        return userMapper.selectByMap(map).get(0);
    }

    @Override
    public boolean existUserName(String username) {
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        return userMapper.selectByMap(map).isEmpty();
    }

    @Override
    public void addNewUser(User newuser) {
        userMapper.insert(newuser);
    }
}
