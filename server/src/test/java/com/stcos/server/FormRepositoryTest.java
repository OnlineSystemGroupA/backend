package com.stcos.server;

import com.stcos.server.database.mongo.FormIndexRepository;
import com.stcos.server.entity.form.Form;
import com.stcos.server.entity.form.FormIndex;
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

    @Autowired
    private FormIndexRepository formIndexRepository;

    @Test
    public void testFind(){
        FormIndex form1 = new FormIndex();
            formIndexRepository.saveFrom(form1);
        List<FormIndex> formIndexList=formIndexRepository.findAll();
        System.out.println(formIndexList);
        List<Form> formList=formRepository.findAll();
        System.out.println(formList);
    }
}
