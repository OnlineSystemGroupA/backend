package com.stcos.server.service.impl;

import com.stcos.server.service.FileMetadataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
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
        // Create a file
        String filePath = "./testFile.txt";
        File file = new File(filePath);
        try {
            boolean isFileCreated = file.createNewFile();
            if (isFileCreated) {
                Long id = fileMetadataService.create("testFileName", "testFileType", file.length(), "testUser", LocalDateTime.now(), filePath);
                assertTrue(fileMetadataService.existFile(id));
            } else {
                fail("File could not be created");
            }
        } catch (IOException e) {
            fail("Exception occurred while creating file: " + e.getMessage());
        } finally {
            // Delete the file
            file.delete();
        }
    }
}
