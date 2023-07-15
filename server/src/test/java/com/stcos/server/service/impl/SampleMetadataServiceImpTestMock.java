package com.stcos.server.service.impl;

import com.stcos.server.database.mysql.SampleMetadataMapper;
import com.stcos.server.model.file.SampleMetadata;
import com.stcos.server.service.SampleMetadataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SampleMetadataServiceImpTestMock {

    @Mock
    private SampleMetadataMapper sampleMetadataMapper;

    @InjectMocks
    private SampleMetadataService sampleMetadataService = new SampleMetadataServiceImp();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        SampleMetadata sampleMetadata = new SampleMetadata();
        when(sampleMetadataMapper.insert(sampleMetadata)).thenReturn(1);

        Long sampleMetadataId = sampleMetadataService.create();
        assertNull(sampleMetadataId);
        verify(sampleMetadataMapper, times(1)).insert(sampleMetadata);
    }

    @Test
    void addFileMetadata() {
        Long sampleMetadataId = 1L;
        Long fileMetadataId = 9876543210L;
        SampleMetadata sampleMetadata = new SampleMetadata();

        when(sampleMetadataMapper.selectById(sampleMetadataId)).thenReturn(sampleMetadata);
        when(sampleMetadataMapper.updateById(sampleMetadata)).thenReturn(1);

        sampleMetadataService.addFileMetadata(sampleMetadataId, fileMetadataId);

        assertTrue(sampleMetadata.getFileMetadataIdList().contains(fileMetadataId));
        verify(sampleMetadataMapper, times(1)).selectById(sampleMetadataId);
        verify(sampleMetadataMapper, times(1)).updateById(sampleMetadata);
    }

    @Test
    void removeFileMetadata() {
        Long sampleMetadataId = 1L;
        Long fileMetadataId = 9876543210L;
        SampleMetadata sampleMetadata = new SampleMetadata();
        sampleMetadata.updateFileMetadataList(fileMetadataId);

        when(sampleMetadataMapper.selectById(sampleMetadataId)).thenReturn(sampleMetadata);
        when(sampleMetadataMapper.updateById(sampleMetadata)).thenReturn(1);

        sampleMetadataService.removeFileMetadata(sampleMetadataId, fileMetadataId);

        assertFalse(sampleMetadata.getFileMetadataIdList().contains(fileMetadataId));
        verify(sampleMetadataMapper, times(1)).selectById(sampleMetadataId);
        verify(sampleMetadataMapper, times(1)).updateById(sampleMetadata);
    }

    @Test
    void addReadPermission() {
        Long sampleMetadataId = 1L;
        String userId = "testUser";
        SampleMetadata sampleMetadata = new SampleMetadata();

        when(sampleMetadataMapper.selectById(sampleMetadataId)).thenReturn(sampleMetadata);
        when(sampleMetadataMapper.updateById(sampleMetadata)).thenReturn(1);

        sampleMetadataService.addReadPermission(sampleMetadataId, userId);

        assertTrue(sampleMetadata.hasReadPermission(userId));
        verify(sampleMetadataMapper, times(1)).selectById(sampleMetadataId);
        verify(sampleMetadataMapper, times(1)).updateById(sampleMetadata);
    }

    @Test
    void addWritePermission() {
        Long sampleMetadataId = 1L;
        String userId = "testUser";
        SampleMetadata sampleMetadata = new SampleMetadata();

        when(sampleMetadataMapper.selectById(sampleMetadataId)).thenReturn(sampleMetadata);
        when(sampleMetadataMapper.updateById(sampleMetadata)).thenReturn(1);

        sampleMetadataService.addWritePermission(sampleMetadataId, userId);

        assertTrue(sampleMetadata.hasWritePermission(userId));
        verify(sampleMetadataMapper, times(1)).selectById(sampleMetadataId);
        verify(sampleMetadataMapper, times(1)).updateById(sampleMetadata);
    }

    @Test
    void removeWritePermission() {
        Long sampleMetadataId = 1L;
        String userId = "testUser";
        SampleMetadata sampleMetadata = new SampleMetadata();
        sampleMetadata.addWritePermission(userId);

        when(sampleMetadataMapper.selectById(sampleMetadataId)).thenReturn(sampleMetadata);
        when(sampleMetadataMapper.updateById(sampleMetadata)).thenReturn(1);

        sampleMetadataService.removeWritePermission(sampleMetadataId, userId);

        assertFalse(sampleMetadata.hasWritePermission(userId));
        verify(sampleMetadataMapper, times(1)).selectById(sampleMetadataId);
        verify(sampleMetadataMapper, times(1)).updateById(sampleMetadata);
    }

    @Test
    void hasReadPermission() {
        Long sampleMetadataId = 1L;
        String userId = "testUser";
        SampleMetadata sampleMetadata = new SampleMetadata();

        when(sampleMetadataMapper.selectById(sampleMetadataId)).thenReturn(sampleMetadata);

        assertFalse(sampleMetadataService.hasReadPermission(sampleMetadataId, userId));
        verify(sampleMetadataMapper, times(1)).selectById(sampleMetadataId);
    }

    @Test
    void hasWritePermission() {
        Long sampleMetadataId = 1L;
        String userId = "testUser";
        SampleMetadata sampleMetadata = new SampleMetadata();

        when(sampleMetadataMapper.selectById(sampleMetadataId)).thenReturn(sampleMetadata);

        assertFalse(sampleMetadataService.hasWritePermission(sampleMetadataId, userId));
        verify(sampleMetadataMapper, times(1)).selectById(sampleMetadataId);
    }

    @Test
    void testCreate() {
        List<String> users = Arrays.asList("user1", "user2");
        SampleMetadata sampleMetadata = new SampleMetadata();
        sampleMetadata.setReadableUsers(users);

        when(sampleMetadataMapper.insert(sampleMetadata)).thenReturn(1);

        Long sampleMetadataId = sampleMetadataService.create(users);
        assertNull(sampleMetadataId);
        verify(sampleMetadataMapper, times(1)).insert(sampleMetadata);
    }
}
