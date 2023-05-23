package com.stcos.server;

import com.stcos.server.entity.form.Form;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.stcos.server.database.mongo.FormRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FormRepositoryTest {

    @Autowired
    private FormRepository formRepository;

    @Test
    public void testFind(){
        List<Form> formList=formRepository.findAll();
        System.out.println(formList);
    }
}
