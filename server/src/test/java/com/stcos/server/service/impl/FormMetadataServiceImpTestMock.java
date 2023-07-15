package com.stcos.server.service.impl;

import com.stcos.server.database.mysql.FormMetadataMapper;
import com.stcos.server.model.form.FormMetadata;
import com.stcos.server.model.form.FormType;
import com.stcos.server.service.FormMetadataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FormMetadataServiceImpTestMock {

    @Mock
    private FormMetadataMapper formMetadataMapper;

    @InjectMocks
    private FormMetadataService formMetadataService = new FormMetadataServiceImp();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        FormMetadata formMetadata = new FormMetadata(1234567890L, FormType.TYPE_TEST_REPORT_FORM);

        when(formMetadataMapper.insert(formMetadata)).thenReturn(1);

        Long formMetadataId = formMetadataService.create(1234567890L, FormType.TYPE_TEST_REPORT_FORM);
        assertNull(formMetadataId);
        verify(formMetadataMapper, times(1)).insert(formMetadata);
    }

    @Test
    void addReadPermission() {
        Long formMetadataId = 1L;
        String userId = "testUser";
        FormMetadata formMetadata = new FormMetadata(1234567890L, FormType.TYPE_TEST_REPORT_FORM);

        when(formMetadataMapper.selectById(formMetadataId)).thenReturn(formMetadata);
        when(formMetadataMapper.updateById(formMetadata)).thenReturn(1);

        formMetadataService.addReadPermission(formMetadataId, userId);

        assertTrue(formMetadata.hasReadPermission(userId));
        verify(formMetadataMapper, times(1)).selectById(formMetadataId);
        verify(formMetadataMapper, times(1)).updateById(formMetadata);
    }

    @Test
    void addReadPermission_set() {
        Long formMetadataId = 1L;
        Set<String> userIds = new HashSet<>();
        userIds.add("testUser1");
        userIds.add("testUser2");
        FormMetadata formMetadata = new FormMetadata(1234567890L, FormType.TYPE_TEST_REPORT_FORM);

        when(formMetadataMapper.selectById(formMetadataId)).thenReturn(formMetadata);
        when(formMetadataMapper.updateById(formMetadata)).thenReturn(1);

        formMetadataService.addReadPermission(formMetadataId, userIds);

        assertTrue(formMetadata.hasReadPermission("testUser1"));
        assertTrue(formMetadata.hasReadPermission("testUser2"));
        verify(formMetadataMapper, times(1)).selectById(formMetadataId);
        verify(formMetadataMapper, times(1)).updateById(formMetadata);
    }

    @Test
    void removeReadPermission() {
        Long formMetadataId = 1L;
        FormMetadata formMetadata = new FormMetadata(1234567890L, FormType.TYPE_TEST_REPORT_FORM);
        formMetadata.addReadPermission("testUser1");
        formMetadata.addReadPermission("testUser2");

        when(formMetadataMapper.selectById(formMetadataId)).thenReturn(formMetadata);
        when(formMetadataMapper.updateById(formMetadata)).thenReturn(1);

        formMetadataService.removeReadPermission(formMetadataId);

        assertFalse(formMetadata.hasReadPermission("testUser1"));
        assertFalse(formMetadata.hasReadPermission("testUser2"));
        verify(formMetadataMapper, times(1)).selectById(formMetadataId);
        verify(formMetadataMapper, times(1)).updateById(formMetadata);
    }

    @Test
    void addWritePermission() {
        Long formMetadataId = 1L;
        String userId = "testUser";
        FormMetadata formMetadata = new FormMetadata(1234567890L, FormType.TYPE_TEST_REPORT_FORM);

        when(formMetadataMapper.selectById(formMetadataId)).thenReturn(formMetadata);
        when(formMetadataMapper.updateById(formMetadata)).thenReturn(1);

        formMetadataService.addWritePermission(formMetadataId, userId);

        assertTrue(formMetadata.hasWritePermission(userId));
        verify(formMetadataMapper, times(1)).selectById(formMetadataId);
        verify(formMetadataMapper, times(1)).updateById(formMetadata);
    }

    @Test
    void removeWritePermission() {
        Long formMetadataId = 1L;
        String userId = "testUser";
        FormMetadata formMetadata = new FormMetadata(1234567890L, FormType.TYPE_TEST_REPORT_FORM);
        formMetadata.addWritePermission(userId);

        when(formMetadataMapper.selectById(formMetadataId)).thenReturn(formMetadata);
        when(formMetadataMapper.updateById(formMetadata)).thenReturn(1);

        formMetadataService.removeWritePermission(formMetadataId, userId);

        assertFalse(formMetadata.hasWritePermission(userId));
        verify(formMetadataMapper, times(1)).selectById(formMetadataId);
        verify(formMetadataMapper, times(1)).updateById(formMetadata);
    }

    @Test
    void removeWritePermission_all() {
        Long formMetadataId = 1L;
        FormMetadata formMetadata = new FormMetadata(1234567890L, FormType.TYPE_TEST_REPORT_FORM);
        formMetadata.addWritePermission("testUser1");
        formMetadata.addWritePermission("testUser2");

        when(formMetadataMapper.selectById(formMetadataId)).thenReturn(formMetadata);
        when(formMetadataMapper.updateById(formMetadata)).thenReturn(1);

        formMetadataService.removeWritePermission(formMetadataId);

        assertFalse(formMetadata.hasWritePermission("testUser1"));
        assertFalse(formMetadata.hasWritePermission("testUser2"));
        verify(formMetadataMapper, times(1)).selectById(formMetadataId);
        verify(formMetadataMapper, times(1)).updateById(formMetadata);
    }

    @Test
    void existForm() {
        Long formMetadataId = 1L;
        FormMetadata formMetadata = new FormMetadata(1234567890L, FormType.TYPE_TEST_REPORT_FORM);

        when(formMetadataMapper.selectById(formMetadataId)).thenReturn(formMetadata);

        assertFalse(formMetadataService.existForm(formMetadataId));
        verify(formMetadataMapper, times(1)).selectById(formMetadataId);
    }

    @Test
    void getFormId() {
        Long formMetadataId = 1L;
        FormMetadata formMetadata = new FormMetadata(1234567890L, FormType.TYPE_TEST_REPORT_FORM);
        formMetadata.setFormId(-1L);

        when(formMetadataMapper.selectById(formMetadataId)).thenReturn(formMetadata);

        Long formId = formMetadataService.getFormId(formMetadataId);
        assertEquals(-1L, formId);
        verify(formMetadataMapper, times(1)).selectById(formMetadataId);
    }

    @Test
    void setFormState() {
        Long formMetadataId = 1L;
        String formState = "state";
        FormMetadata formMetadata = new FormMetadata(1234567890L, FormType.TYPE_TEST_REPORT_FORM);

        when(formMetadataMapper.selectById(formMetadataId)).thenReturn(formMetadata);
        when(formMetadataMapper.updateById(formMetadata)).thenReturn(1);

        formMetadataService.setFormState(formMetadataId, formState);

        assertEquals(formState, formMetadata.getFormState());
        verify(formMetadataMapper, times(1)).selectById(formMetadataId);
        verify(formMetadataMapper, times(1)).updateById(formMetadata);
    }
}