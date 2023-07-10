package com.stcos.server.service.impl;

import com.stcos.server.service.FileMetadataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FileMetadataServiceImpTest {

    @Autowired
    private FileMetadataService fileMetadataService;

    @Test
    void create() {
        Long id = fileMetadataService.create("testFileName", "testFileType", 123L, "testUser", LocalDateTime.now(), "testFilePath");
        assertNotNull(id);
    }

    @Test
    void existFile() {
        Long id = fileMetadataService.create("testFileName", "testFileType", 123L, "testUser", LocalDateTime.now(), "testFilePath");
        assertTrue(fileMetadataService.existFile(id));
    }
}
