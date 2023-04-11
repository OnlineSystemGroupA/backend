package com.stcos.server;

import com.stcos.server.database.mapper.UserMapper;
import com.stcos.server.pojo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Unit test for simple App.
 */
@SpringBootTest
public class ServerApplicationTest{

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList(){
        //query a list through wrappers
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }
}
