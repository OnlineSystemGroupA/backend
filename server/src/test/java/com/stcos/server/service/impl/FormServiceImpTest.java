package com.stcos.server.service.impl;

import com.stcos.server.database.mongo.FormMetadataRepository;
import com.stcos.server.entity.form.Form;
import com.stcos.server.entity.form.FormMetadata;
import com.stcos.server.entity.form.TestReportForm;
import com.stcos.server.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class FormServiceImpTest {

    @Autowired
    private FormMetadataRepository formMetadataRepository;

    @Autowired
    private FormServiceImp formServiceImp;

    @Test
    void formServiceTest() {

        Form form = new TestReportForm("softwareName", "softwareVersion");

        LocalDateTime localDateTime = LocalDateTime.now();

        FormMetadata formMetadata = new FormMetadata("form1","masterCheDan");

        System.out.println(formMetadata);

        List<String> writableUsers = new ArrayList<>();
        writableUsers.add("writableUser");
        formMetadata.setWritableUsers(writableUsers);

        try {
            formServiceImp.(25L, "TestReportForm", form);
        } catch (ServiceException e) {
            System.out.println("Error code: " + e.getCode());
            return;
        }

        try {
            formServiceImp.updateForm(25L, "TestReportForm", form);
        } catch (ServiceException e) {
            System.out.println("Error code: " + e.getCode());
            return;
        }

        System.out.println("Update successful");

        List<String> readableUsers = new ArrayList<>();
        readableUsers.add("readableUser");
        formMetadata.setReadableUsers(readableUsers);

        try {
            System.out.println(formServiceImp.getForm(25L));
        } catch (ServiceException e) {
            System.out.println("Error code: " + e.getCode());
        }
    }
}