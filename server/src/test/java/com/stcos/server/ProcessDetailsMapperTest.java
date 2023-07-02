package com.stcos.server;

import com.stcos.server.database.mysql.ProcessDetailsMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessDetailsMapperTest {
    @Autowired
    private ProcessDetailsMapper processDetailsMapper;
}
