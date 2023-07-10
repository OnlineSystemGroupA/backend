package com.stcos.server.service.impl;

import com.stcos.server.database.mysql.FormMetadataMapper;
import com.stcos.server.entity.form.FormMetadata;
import com.stcos.server.entity.form.FormType;
import com.stcos.server.service.FormMetadataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class FormMetadataServiceImpTest {
    
    @Autowired
    private FormMetadataService formMetadataService;

    @Test
    void create() {
        Long formMetadataId = formMetadataService.create(FormType.TYPE_TEST_REPORT_FORM);
        assertNotNull(formMetadataId);
    }

    @Test
    void addReadPermission() {
        Long formMetadataId = formMetadataService.create(FormType.TYPE_TEST_REPORT_FORM);
        formMetadataService.addReadPermission(formMetadataId, "testUser");
        assertTrue(formMetadataService.getById(formMetadataId).hasReadPermission("testUser"));
    }

    @Test
    void addWritePermission() {
        Long formMetadataId = formMetadataService.create(FormType.TYPE_TEST_REPORT_FORM);
        formMetadataService.addWritePermission(formMetadataId, "testUser");
        assertTrue(formMetadataService.getById(formMetadataId).hasWritePermission("testUser"));
    }

    @Test
    void removeWritePermission() {
        Long formMetadataId = formMetadataService.create(FormType.TYPE_TEST_REPORT_FORM);
        formMetadataService.addWritePermission(formMetadataId, "testUser");
        assertTrue(formMetadataService.getById(formMetadataId).hasWritePermission("testUser"));
        formMetadataService.removeWritePermission(formMetadataId, "testUser");
        assertFalse(formMetadataService.getById(formMetadataId).hasWritePermission("testUser"));
    }

    @Test
    void existForm() {
        Long formMetadataId = formMetadataService.create(FormType.TYPE_TEST_REPORT_FORM);
        assertFalse(formMetadataService.existForm(formMetadataId));
    }

    @Test
    void getFormId() {
        Long formMetadataId = formMetadataService.create(FormType.TYPE_TEST_REPORT_FORM);
        Long formId = formMetadataService.getFormId(formMetadataId);
        assertEquals(-1, formId);
    }

    @Test
    void testCreate() {
        List<String> users = Arrays.asList("user1", "user2");
        Long formMetadataId = formMetadataService.create(FormType.TYPE_TEST_REPORT_FORM, users);
        FormMetadata formMetadata = formMetadataService.getById(formMetadataId);
        assertTrue(formMetadata.hasReadPermission("user1"));
        assertTrue(formMetadata.hasReadPermission("user2"));
    }
}