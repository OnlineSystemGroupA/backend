package com.stcos.server.database.impl;

import com.stcos.server.database.UserRepo;
import com.stcos.server.database.mapper.UserMapper;
import com.stcos.server.pojo.po.TempUser;
import com.stcos.server.pojo.po.Customer;
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


    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper){this.userMapper=userMapper;}

    @Override
    public Customer getUserByName(String username){
        Map<String, Object> map = new HashMap<>();
        map.put("username",username);
        if(userMapper.selectByMap(map).isEmpty())
            return null;
        else
            return userMapper.selectByMap(map).get(0);
    }

    @Override
    public boolean existUsername(String username) {
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        return !userMapper.selectByMap(map).isEmpty();
    }

    @Override
    public void addNewUser(Customer newuser) {
        userMapper.insert(newuser);
    }

    @Override
    public void addNewUser(TempUser newuser){
        this.addNewUser(new Customer(newuser.getName(), newuser.getPassword(), newuser.getEmail()));
    }
}
