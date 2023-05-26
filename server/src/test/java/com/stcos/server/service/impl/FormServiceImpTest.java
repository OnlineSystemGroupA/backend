package com.stcos.server.service.impl;

import com.stcos.server.config.security.User;
import com.stcos.server.entity.form.Form;
import com.stcos.server.entity.form.FormMetadata;
import com.stcos.server.entity.form.TestReportForm;
import com.stcos.server.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FormServiceImpTest {

    @Autowired
    private com.stcos.server.repository.FormMetadataRepository formMetadataRepository;

    @Autowired
    private FormServiceImp formServiceImp;

    @Test
    void formServiceTest() throws ServiceException {

        Form form = new TestReportForm("softwareName", "softwareVersion");

        LocalDateTime localDateTime = LocalDateTime.now();

//        FormMetadata formMetadata = new FormMetadata(
//                111L,
//                "TestReportForm",
//                "client",
//                localDateTime,
//                "client",
//                localDateTime
//        );
        FormMetadata formMetadata = formMetadataRepository.findByFormMetadataId(25L);

        System.out.println(formMetadata);

        List<String> writableUsers = new ArrayList<>();
        writableUsers.add("writableUser");
        formMetadata.setWritableUsers(writableUsers);

        try {
            formServiceImp.updateForm(formMetadata, "TestReportForm", form);
        } catch (ServiceException e) {
            System.out.println("Error code: " + e.getCode());
            return;
        }

        System.out.println("Update successful");

        List<String> readableUsers = new ArrayList<>();
        readableUsers.add("readableUser");
        formMetadata.setReadableUsers(readableUsers);

        try {
            System.out.println(formServiceImp.getForm(formMetadata));
        } catch (ServiceException e) {
            System.out.println("Error code: " + e.getCode());
        }
    }
}