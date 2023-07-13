package com.stcos.server.service.impl;

import com.stcos.server.database.mysql.FileMetadataMapper;
import com.stcos.server.entity.file.FileMetadata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileMetadataServiceImpTestMock {

    @InjectMocks
    private FileMetadataServiceImp fileMetadataService;

    @Mock
    private FileMetadataMapper fileMetadataMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void create() {
        String fileName = "testFileName";
        String fileType = "testFileType";
        Long fileSize = 123L;
        String updatedBy = "testUser";
        LocalDateTime updatedDate = LocalDateTime.now();
        String filePath = "testFilePath";

        FileMetadata fileMetadata = new FileMetadata(fileName, fileType, fileSize, updatedBy, updatedDate, filePath);
        when(fileMetadataMapper.insert(fileMetadata)).thenReturn(1);

        Long id = fileMetadataService.create(fileName, fileType, fileSize, updatedBy, updatedDate, filePath);

        assertNull(id);
    }

    @Test
    void existFile() {
        Long fileMetadataId = 1L;
        String filePath = "testFilePath";

        FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setFilePath(filePath);

        when(fileMetadataService.getById(fileMetadataId)).thenReturn(fileMetadata);

        assertFalse(fileMetadataService.existFile(fileMetadataId));
    }
}
