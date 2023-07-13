package com.stcos.server.service.impl;

import com.stcos.server.database.mongo.FormRepository;
import com.stcos.server.entity.form.Form;
import com.stcos.server.entity.form.FormMetadata;
import com.stcos.server.entity.form.FormType;
import com.stcos.server.entity.form.TestReportForm;
import com.stcos.server.entity.user.Client;
import com.stcos.server.entity.user.User;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.service.FormMetadataService;
import com.stcos.server.service.FormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class FormServiceImpTestMock {

    @Mock
    private FormMetadataService formMetadataService;

    @Mock
    private FormRepository formRepository;

    @InjectMocks
    private FormServiceImp formService;

    private Long formMetadataId;

    private FormMetadata formMetadata;

    private Form form;
    private Client mockUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        formMetadataId = 1L;
        formMetadata = new FormMetadata(1L, FormType.TYPE_TEST_REPORT_FORM);
        formMetadata.setFormId(1L);

        form = new TestReportForm();

        mockUser = new Client("testClientName", "testPassword", "testEmail@test.com");
        mockUser.setUid("testUser");

        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(mockUser);

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void getFormWithReadPermission() throws ServiceException {
        when(formMetadataService.getById(formMetadataId)).thenReturn(formMetadata);
        when(formRepository.getFormById(formMetadata.getFormId())).thenReturn(form);

        formMetadata.addReadPermission(mockUser.getUid());

        Form retrievedForm = formService.getForm(formMetadataId, mockUser.getUid());
        assertEquals(form, retrievedForm);

        verify(formRepository, times(1)).getFormById(formMetadata.getFormId());
    }

    @Test
    void getFormWithoutReadPermission() {
        when(formMetadataService.getById(formMetadataId)).thenReturn(formMetadata);

        assertThrows(ServiceException.class, () -> {
            formService.getForm(formMetadataId, mockUser.getUid());
        });

        verify(formMetadataService, times(1)).getById(formMetadataId);
        verifyNoInteractions(formRepository);
    }

    @Test
    void saveOrUpdateFormWithWritePermission() throws ServiceException {
        when(formMetadataService.getById(formMetadataId)).thenReturn(formMetadata);
        when(formRepository.save(form)).thenReturn(form);
        when(formMetadataService.updateById(formMetadata)).thenReturn(true);

        formMetadata.addWritePermission(mockUser.getUid());

        formService.saveOrUpdateForm(formMetadataId, form);

        verify(formMetadataService, times(1)).getById(formMetadataId);
        verify(formRepository, times(1)).save(form);
        verify(formMetadataService, times(1)).updateById(formMetadata);
    }

    @Test
    void saveOrUpdateFormWithoutWritePermission() {
        when(formMetadataService.getById(formMetadataId)).thenReturn(formMetadata);

        assertThrows(ServiceException.class, () -> {
            formService.saveOrUpdateForm(formMetadataId, form);
        });

        verify(formMetadataService, times(1)).getById(formMetadataId);
        verifyNoInteractions(formRepository);
    }

    @Test
    void createMetadata() {
        Long projectId = 1L;
        String formType = "testFormType";

        when(formMetadataService.create(projectId, formType)).thenReturn(formMetadataId);

        Long createdFormMetadataId = formService.createMetadata(projectId, formType);

        assertEquals(formMetadataId, createdFormMetadataId);

        verify(formMetadataService, times(1)).create(projectId, formType);
    }

    @Test
    void existForm() {
        when(formMetadataService.existForm(formMetadataId)).thenReturn(true);

        assertTrue(formService.existForm(formMetadataId));

        verify(formMetadataService, times(1)).existForm(formMetadataId);
    }

    @Test
    void hasWritePermission() {
        when(formMetadataService.getById(formMetadataId)).thenReturn(formMetadata);

        formMetadata.addWritePermission(mockUser.getUid());

        assertTrue(formService.hasWritePermission(formMetadataId, mockUser.getUid()));

        verify(formMetadataService, times(1)).getById(formMetadataId);
    }

    @Test
    void hasReadPermission() {
        when(formMetadataService.getById(formMetadataId)).thenReturn(formMetadata);

        formMetadata.addReadPermission(mockUser.getUid());

        assertTrue(formService.hasReadPermission(formMetadataId, mockUser.getUid()));

        verify(formMetadataService, times(1)).getById(formMetadataId);
    }

    @Test
    void getCreatedDate() {
        LocalDateTime createdDate = LocalDateTime.now();

        formMetadata.setCreatedDate(createdDate);

        when(formMetadataService.getById(formMetadataId)).thenReturn(formMetadata);

        assertEquals(createdDate, formService.getCreatedDate(formMetadataId));

        verify(formMetadataService, times(1)).getById(formMetadataId);
    }

    @Test
    void getFormState() {
        String formState = "testFormState";

        formMetadata.setFormState(formState);

        when(formMetadataService.getById(formMetadataId)).thenReturn(formMetadata);

        assertEquals(formState, formService.getFormState(formMetadataId));

        verify(formMetadataService, times(1)).getById(formMetadataId);
    }
}