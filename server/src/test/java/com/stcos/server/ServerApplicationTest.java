package com.stcos.server;

import com.stcos.server.mapper.ClientMapper;
import com.stcos.server.pojo.po.Client;
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
    private ClientMapper clientMapper;

    @Test
    public void testSelectList(){
        //query a list through wrappers
        List<Client> list = clientMapper.selectList(null);
        list.forEach(System.out::println);
    }

}
