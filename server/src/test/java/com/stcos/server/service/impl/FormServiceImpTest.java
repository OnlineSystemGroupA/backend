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

    @BeforeEach
    void setUp() {
        // Prepare test data
        formMetadataId = formMetadataService.create(1L, FormType.TYPE_TEST_REPORT_FORM);
        form = new TestReportForm();
        form.setSoftwareName("testSoftwareName");

        // Create a User object
        mockUser = new Client("testUser", "testPassword", "testEmail@test.com");
        mockUser.setUid("mockUser.getUid()");

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
    void saveOrUpdateFormWithInvalidPermission() {
        // Expect a ServiceException to be thrown
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            formService.saveOrUpdateForm(formMetadataId, form);
        });
        assertEquals(1, exception.getCode());
    }

    @Test
    void saveOrUpdateFormSuccessful() throws ServiceException {
        // Simulate the current logged-in user having write permission
        formMetadataService.addWritePermission(formMetadataId, mockUser.getUid()); // Test "addWritePermission()"

        formService.saveOrUpdateForm(formMetadataId, form);

        // Update the form
        form.setSoftwareVersion("Version 1.0");

        formService.saveOrUpdateForm(formMetadataId, form);
    }

    @Test
    void getFormWithInvalidPermission() {
        // Expect a ServiceException to be thrown
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            formService.getForm(formMetadataId, mockUser.getUid());
        });
        assertEquals(1, exception.getCode());
    }

    @Test
    void getFormSuccessful() throws Exception {
        // Simulate the current logged-in user having write permission
        formService.addWritePermission(formMetadataId, mockUser.getUid());

        formService.saveOrUpdateForm(formMetadataId, form);

        // Simulate the current logged-in user having read permission
        formService.addReadPermission(formMetadataId, mockUser.getUid());

        TestReportForm retrievedForm = (TestReportForm) formService.getForm(formMetadataId, mockUser.getUid());
        assertEquals(form.getSoftwareName(), retrievedForm.getSoftwareName());
    }

    @Test
    void existForm() throws ServiceException {
        formService.addWritePermission(formMetadataId, mockUser.getUid());
        assertFalse(formService.existForm(formMetadataId));

        formService.saveOrUpdateForm(formMetadataId, form);
        assertTrue(formService.existForm(formMetadataId));
    }

    @Test
    void addWritePermission() {
        assertFalse(formService.hasWritePermission(formMetadataId, mockUser.getUid()));

        formService.addWritePermission(formMetadataId, mockUser.getUid());
        assertTrue(formService.hasWritePermission(formMetadataId, mockUser.getUid()));
    }

    @Test
    void addReadPermission() {
        assertFalse(formService.hasReadPermission(formMetadataId, mockUser.getUid()));

        formService.addReadPermission(formMetadataId, mockUser.getUid());
        assertTrue(formService.hasReadPermission(formMetadataId, mockUser.getUid()));
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
        Long projectId = 1234567890L;
        String formType = FormType.TYPE_TEST_REPORT_FORM;

        Long createdFormMetadataId = formService.createMetadata(projectId, formType);

        FormMetadata formMetadata = formMetadataService.getById(createdFormMetadataId);
        assertEquals(projectId, formMetadata.getProjectId());
        assertEquals(formType, formMetadata.getFormType());
    }

    @Test
    void removeWritePermission() {
        formService.addWritePermission(formMetadataId, mockUser.getUid());
        assertTrue(formService.hasWritePermission(formMetadataId, mockUser.getUid()));

        formService.removeWritePermission(formMetadataId, mockUser.getUid());
        assertFalse(formService.hasWritePermission(formMetadataId, mockUser.getUid()));
    }

    @Test
    void testRemoveWritePermission() {
        formService.addWritePermission(formMetadataId, mockUser.getUid());
        assertTrue(formService.hasWritePermission(formMetadataId, mockUser.getUid()));

        formService.removeWritePermission(formMetadataId);
        assertFalse(formService.hasWritePermission(formMetadataId, mockUser.getUid()));
    }

    @Test
    void getForm() throws ServiceException {
        formService.addWritePermission(formMetadataId, mockUser.getUid());
        formService.saveOrUpdateForm(formMetadataId, form);

        Form retrievedForm = formService.getForm(formMetadataId);
        assertEquals(form.getSoftwareName(), ((TestReportForm) retrievedForm).getSoftwareName());
    }

    @Test
    void hasWritePermission() {
        assertFalse(formService.hasWritePermission(formMetadataId, mockUser.getUid()));

        formService.addWritePermission(formMetadataId, mockUser.getUid());
        assertTrue(formService.hasWritePermission(formMetadataId, mockUser.getUid()));
    }

    @Test
    void hasReadPermission() {
        assertFalse(formService.hasReadPermission(formMetadataId, mockUser.getUid()));

        formService.addReadPermission(formMetadataId, mockUser.getUid());
        assertTrue(formService.hasReadPermission(formMetadataId, mockUser.getUid()));
    }

    @Test
    void getCreatedDate() throws ServiceException {
        formMetadataService.addWritePermission(formMetadataId, mockUser.getUid());
        formService.saveOrUpdateForm(formMetadataId, form);
        assertNotNull(formService.getCreatedDate(formMetadataId));
    }

    @Test
    void getFormState() throws ServiceException {
        formMetadataService.addWritePermission(formMetadataId, mockUser.getUid());
        formService.saveOrUpdateForm(formMetadataId, form);

        String expectedState = FormState.STATE_NULL;
        assertEquals(expectedState, formService.getFormState(formMetadataId));
    }

    @Test
    void setFormState() throws ServiceException {
        formMetadataService.addWritePermission(formMetadataId, mockUser.getUid());
        formService.saveOrUpdateForm(formMetadataId, form);

        String newState = FormState.STATE_COMPLETED;
        formService.setFormState(formMetadataId, newState);

        FormMetadata formMetadata = formMetadataService.getById(formMetadataId);
        assertEquals(newState, formMetadata.getFormState());
    }
}