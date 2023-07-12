package com.stcos.server.service.impl;

import com.stcos.server.model.form.FormType;
import com.stcos.server.service.FormMetadataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class FormMetadataServiceImpTest {
    
    @Autowired
    private FormMetadataService formMetadataService;

    @Test
    void create() {
        Long formMetadataId = formMetadataService.create(1L, FormType.TYPE_TEST_REPORT_FORM);
        assertNotNull(formMetadataId);
    }

    @Test
    void addReadPermission() {
        Long formMetadataId = formMetadataService.create(1L, FormType.TYPE_TEST_REPORT_FORM);
        formMetadataService.addReadPermission(formMetadataId, "testUser");
        assertTrue(formMetadataService.getById(formMetadataId).hasReadPermission("testUser"));
    }

    @Test
    public void testAddReadPermission() {
        Set<String> users = new HashSet<>();
        users.add("testUser1");
        users.add("testUser2");

        Long formMetadataId = formMetadataService.create(1L, FormType.TYPE_TEST_REPORT_FORM);
        formMetadataService.addReadPermission(formMetadataId, users);
        assertTrue(formMetadataService.getById(formMetadataId).hasReadPermission("testUser1"));
        assertTrue(formMetadataService.getById(formMetadataId).hasReadPermission("testUser2"));
    }

    @Test
    void removeReadPermission() {
        Long formMetadataId = formMetadataService.create(1L, FormType.TYPE_TEST_REPORT_FORM);
        formMetadataService.addReadPermission(formMetadataId, "testUser1");
        formMetadataService.addReadPermission(formMetadataId, "testUser2");
        assertTrue(formMetadataService.getById(formMetadataId).hasReadPermission("testUser1"));
        assertTrue(formMetadataService.getById(formMetadataId).hasReadPermission("testUser2"));
        formMetadataService.removeReadPermission(formMetadataId);
        assertFalse(formMetadataService.getById(formMetadataId).hasReadPermission("testUser1"));
        assertFalse(formMetadataService.getById(formMetadataId).hasReadPermission("testUser2"));
    }

    @Test
    void addWritePermission() {
        Long formMetadataId = formMetadataService.create(1L, FormType.TYPE_TEST_REPORT_FORM);
        formMetadataService.addWritePermission(formMetadataId, "testUser");
        assertTrue(formMetadataService.getById(formMetadataId).hasWritePermission("testUser"));
    }

    @Test
    void removeWritePermission() {
        Long formMetadataId = formMetadataService.create(1L, FormType.TYPE_TEST_REPORT_FORM);
        formMetadataService.addWritePermission(formMetadataId, "testUser");
        assertTrue(formMetadataService.getById(formMetadataId).hasWritePermission("testUser"));
        formMetadataService.removeWritePermission(formMetadataId, "testUser");
        assertFalse(formMetadataService.getById(formMetadataId).hasWritePermission("testUser"));
    }

    @Test
    void testRemoveWritePermission() {
        Long formMetadataId = formMetadataService.create(1L, FormType.TYPE_TEST_REPORT_FORM);
        formMetadataService.addWritePermission(formMetadataId, "testUser1");
        formMetadataService.addWritePermission(formMetadataId, "testUser2");
        assertTrue(formMetadataService.getById(formMetadataId).hasWritePermission("testUser1"));
        assertTrue(formMetadataService.getById(formMetadataId).hasWritePermission("testUser2"));
        formMetadataService.removeWritePermission(formMetadataId);
        assertFalse(formMetadataService.getById(formMetadataId).hasWritePermission("testUser1"));
        assertFalse(formMetadataService.getById(formMetadataId).hasWritePermission("testUser2"));}

    @Test
    void existForm() {
        Long formMetadataId = formMetadataService.create(1L, FormType.TYPE_TEST_REPORT_FORM);
        assertFalse(formMetadataService.existForm(formMetadataId));
    }

    @Test
    void getFormId() {
        Long formMetadataId = formMetadataService.create(1L, FormType.TYPE_TEST_REPORT_FORM);
        Long formId = formMetadataService.getFormId(formMetadataId);
        assertEquals(-1, formId);
    }
}