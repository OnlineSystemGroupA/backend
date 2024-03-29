package com.stcos.server.service.impl;

import com.stcos.server.service.SampleMetadataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class SampleMetadataServiceImpTest {

    @Autowired
    private SampleMetadataService sampleMetadataService;

    @Test
    void create() {
        Long sampleMetadataId = sampleMetadataService.create();
        assertNotNull(sampleMetadataId);
    }

    @Test
    void addFileMetadata() {
        Long sampleMetadataId = sampleMetadataService.create();
        Long fileMetadataId = 9876543210L;
        sampleMetadataService.addFileMetadata(sampleMetadataId, fileMetadataId);
        assertTrue(sampleMetadataService.getById(sampleMetadataId).getFileMetadataIdList().contains(fileMetadataId));
    }

    @Test
    void removeFileMetadata() {
        Long sampleMetadataId = sampleMetadataService.create();
        Long fileMetadataId = 9876543210L;
        sampleMetadataService.addFileMetadata(sampleMetadataId, fileMetadataId);
        assertTrue(sampleMetadataService.getById(sampleMetadataId).getFileMetadataIdList().contains(fileMetadataId));
        sampleMetadataService.removeFileMetadata(sampleMetadataId, fileMetadataId);
        assertFalse(sampleMetadataService.getById(sampleMetadataId).getFileMetadataIdList().contains(fileMetadataId));
    }

    @Test
    void addReadPermission() {
        Long sampleMetadataId = sampleMetadataService.create();
        String userId = "testUser";
        sampleMetadataService.addReadPermission(sampleMetadataId, userId);
        assertTrue(sampleMetadataService.hasReadPermission(sampleMetadataId, userId));
    }

    @Test
    void addWritePermission() {
        Long sampleMetadataId = sampleMetadataService.create();
        String userId = "testUser";
        sampleMetadataService.addWritePermission(sampleMetadataId, userId);
        assertTrue(sampleMetadataService.hasWritePermission(sampleMetadataId, userId));
    }

    @Test
    void removeWritePermission() {
        Long sampleMetadataId = sampleMetadataService.create();
        String userId = "testUser";
        sampleMetadataService.addWritePermission(sampleMetadataId, userId);
        sampleMetadataService.removeWritePermission(sampleMetadataId, userId);
        assertFalse(sampleMetadataService.hasWritePermission(sampleMetadataId, userId));
    }

    @Test
    void hasReadPermission() {
        Long sampleMetadataId = sampleMetadataService.create();
        String userId = "testUser";
        assertFalse(sampleMetadataService.hasReadPermission(sampleMetadataId, userId));
        sampleMetadataService.addReadPermission(sampleMetadataId, userId);
        assertTrue(sampleMetadataService.hasReadPermission(sampleMetadataId, userId));
    }

    @Test
    void hasWritePermission() {
        Long sampleMetadataId = sampleMetadataService.create();
        String userId = "testUser";
        assertFalse(sampleMetadataService.hasWritePermission(sampleMetadataId, userId));
        sampleMetadataService.addWritePermission(sampleMetadataId, userId);
        assertTrue(sampleMetadataService.hasWritePermission(sampleMetadataId, userId));
    }

    @Test
    void testCreate() {
        List<String> users = Arrays.asList("user1", "user2");
        Long sampleMetadataId = sampleMetadataService.create(users);
        assertTrue(sampleMetadataService.hasReadPermission(sampleMetadataId, "user1"));
        assertTrue(sampleMetadataService.hasReadPermission(sampleMetadataId, "user2"));
    }
}
