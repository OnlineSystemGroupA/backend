package com.stcos.server.service.impl;

import com.stcos.server.entity.form.*;
import com.stcos.server.entity.user.Client;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.service.FormMetadataService;
import com.stcos.server.service.FormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
public class FormServiceImpTest {

    @Autowired
    private FormService formService;

    @Autowired
    private FormMetadataService formMetadataService;

    Long formMetadataId = null;

    TestReportForm form = null;

    Client mockUser = null;

    String testUid = "testUid";

    @BeforeEach
    void setUp() {
        // Prepare test data
        formMetadataId = formMetadataService.create(1L, FormType.TYPE_TEST_REPORT_FORM);
        form = new TestReportForm();
        form.setSoftwareName("testSoftwareName");

        // Create a User object
        mockUser = new Client("testUser", "testPassword", "testEmail@test.com");
        mockUser.setUid(testUid);

        // Create a mock Authentication object
        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(mockUser);

        // Create a mock SecurityContext object
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        // Set the mock SecurityContext object to SecurityContextHolder
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void saveOrUpdateForm_WithoutWritePermission_ThrowsServiceException() {
        // Expect a ServiceException to be thrown
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            formService.saveOrUpdateForm(formMetadataId, form);
        });
        assertEquals(1, exception.getCode());
    }

    @Test
    void saveOrUpdateForm_WithWritePermission() throws ServiceException {
        // Simulate the current logged-in user having write permission
        formMetadataService.addWritePermission(formMetadataId, testUid); // Test "addWritePermission()"

        formService.saveOrUpdateForm(formMetadataId, form);

        // Update the form
        form.setSoftwareVersion("Version 1.0");

        formService.saveOrUpdateForm(formMetadataId, form);
    }

    @Test
    void getForm_WithoutReadPermission_ThrowsServiceException() throws Exception {
        // Expect a ServiceException to be thrown
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            formService.getForm(formMetadataId, mockUser.getUid());
        });
        assertEquals(1, exception.getCode());
    }

    @Test
    void getForm_WithReadPermission_ThrowsServiceException() throws Exception {
        // Simulate the current logged-in user having write permission
        formService.addWritePermission(formMetadataId, testUid);

        formService.saveOrUpdateForm(formMetadataId, form);

        // Simulate the current logged-in user having read permission
        formService.addReadPermission(formMetadataId, testUid);

        TestReportForm retrievedForm = (TestReportForm) formService.getForm(formMetadataId, mockUser.getUid());
        assertEquals(form.getSoftwareName(), retrievedForm.getSoftwareName());
    }

    @Test
    void existForm() throws ServiceException {
        formService.addWritePermission(formMetadataId, testUid);
        assertFalse(formService.existForm(formMetadataId));

        formService.saveOrUpdateForm(formMetadataId, form);
        assertTrue(formService.existForm(formMetadataId));
    }

    @Test
    void addWritePermission() {
        assertFalse(formService.hasWritePermission(formMetadataId, testUid));

        formService.addWritePermission(formMetadataId, testUid);
        assertTrue(formService.hasWritePermission(formMetadataId, testUid));
    }

    @Test
    void addReadPermission() {
        assertFalse(formService.hasReadPermission(formMetadataId, testUid));

        formService.addReadPermission(formMetadataId, testUid);
        assertTrue(formService.hasReadPermission(formMetadataId, testUid));
    }

    @Test
    void testAddReadPermission1() {
        assertFalse(formService.hasReadPermission(formMetadataId, "testUser1"));
        assertFalse(formService.hasReadPermission(formMetadataId, "testUser2"));

        Set<String> users = new HashSet<>();
        users.add("testUser1");
        users.add("testUser2");

        formService.addReadPermission(formMetadataId, users);

        assertTrue(formService.hasReadPermission(formMetadataId, "testUser1"));
        assertTrue(formService.hasReadPermission(formMetadataId, "testUser2"));
    }

    @Test
    void removeReadPermission() {
        Set<String> users = new HashSet<>();
        users.add("testUser1");
        users.add("testUser2");

        formService.addReadPermission(formMetadataId, users);

        assertTrue(formService.hasReadPermission(formMetadataId, "testUser1"));
        assertTrue(formService.hasReadPermission(formMetadataId, "testUser2"));

        formService.removeReadPermission(formMetadataId);

        assertFalse(formService.hasReadPermission(formMetadataId, "testUser1"));
        assertFalse(formService.hasReadPermission(formMetadataId, "testUser2"));
    }

    @Test
    void createMetadata() {
        Long projectId = 1L;
        String formType = FormType.TYPE_TEST_REPORT_FORM;

        Long createdFormMetadataId = formService.createMetadata(projectId, formType);

        FormMetadata formMetadata = formMetadataService.getById(createdFormMetadataId);
        assertEquals(projectId, formMetadata.getProjectId());
        assertEquals(formType, formMetadata.getFormType());
    }

    @Test
    void removeWritePermission() {
        formService.addWritePermission(formMetadataId, testUid);
        assertTrue(formService.hasWritePermission(formMetadataId, testUid));

        formService.removeWritePermission(formMetadataId, testUid);
        assertFalse(formService.hasWritePermission(formMetadataId, testUid));
    }

    @Test
    void testRemoveWritePermission() {
        formService.addWritePermission(formMetadataId, testUid);
        assertTrue(formService.hasWritePermission(formMetadataId, testUid));

        formService.removeWritePermission(formMetadataId);
        assertFalse(formService.hasWritePermission(formMetadataId, testUid));
    }

    @Test
    void getForm() throws ServiceException {
        formService.addWritePermission(formMetadataId, testUid);
        formService.saveOrUpdateForm(formMetadataId, form);

        Form retrievedForm = formService.getForm(formMetadataId);
        assertEquals(form.getSoftwareName(), ((TestReportForm) retrievedForm).getSoftwareName());
    }

    @Test
    void hasWritePermission() {
        assertFalse(formService.hasWritePermission(formMetadataId, testUid));

        formService.addWritePermission(formMetadataId, testUid);
        assertTrue(formService.hasWritePermission(formMetadataId, testUid));
    }

    @Test
    void hasReadPermission() {
        assertFalse(formService.hasReadPermission(formMetadataId, testUid));

        formService.addReadPermission(formMetadataId, testUid);
        assertTrue(formService.hasReadPermission(formMetadataId, testUid));
    }

    @Test
    void getCreatedDate() throws ServiceException {
        formMetadataService.addWritePermission(formMetadataId, testUid);
        formService.saveOrUpdateForm(formMetadataId, form);
        assertNotNull(formService.getCreatedDate(formMetadataId));
    }

    @Test
    void getFormState() throws ServiceException {
        formMetadataService.addWritePermission(formMetadataId, testUid);
        formService.saveOrUpdateForm(formMetadataId, form);

        String expectedState = FormState.STATE_NULL;
        assertEquals(expectedState, formService.getFormState(formMetadataId));
    }

    @Test
    void setFormState() throws ServiceException {
        formMetadataService.addWritePermission(formMetadataId, testUid);
        formService.saveOrUpdateForm(formMetadataId, form);

        String newState = FormState.STATE_COMPLETED;
        formService.setFormState(formMetadataId, newState);

        FormMetadata formMetadata = formMetadataService.getById(formMetadataId);
        assertEquals(newState, formMetadata.getFormState());
    }
}