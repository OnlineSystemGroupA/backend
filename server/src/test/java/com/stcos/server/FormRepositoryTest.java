package com.stcos.server;

import com.stcos.server.database.mongo.FormMetadataRepository;
import com.stcos.server.entity.form.ApplicationForm;
import com.stcos.server.entity.form.Form;
import com.stcos.server.entity.form.FormMetadata;
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
    private FormMetadataRepository formMetadataRepository;

    @Test
    public void testFind(){
        FormMetadata form1 = new FormMetadata();
            formMetadataRepository.saveFormMetadata(form1);

        List<FormMetadata> formMetadataList=formMetadataRepository.findAll();
        System.out.println(formMetadataList);

        Form form = new ApplicationForm();
            formRepository.saveForm(form);

        List<Form> formList=formRepository.findAll();
        System.out.println(formList);
    }
}
