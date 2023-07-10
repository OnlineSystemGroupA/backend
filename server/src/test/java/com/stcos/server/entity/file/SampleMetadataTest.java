package com.stcos.server.entity.file;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SampleMetadataTest {

    private SampleMetadata sampleMetadata;

    @BeforeEach
    void setUp() {
        sampleMetadata = new SampleMetadata();
    }

    @Test
    void updateFileMetadataList() {
        sampleMetadata.updateFileMetadataList(1L);
        assertTrue(sampleMetadata.getFileMetadataIdList().contains(1L));
    }

    @Test
    void hasReadPermission() {
        assertFalse(sampleMetadata.hasReadPermission("test"));
        sampleMetadata.addReadPermission("test");
        assertTrue(sampleMetadata.hasReadPermission("test"));
    }

    @Test
    void hasWritePermission() {
        assertFalse(sampleMetadata.hasWritePermission("test"));
        sampleMetadata.addWritePermission("test");
        assertTrue(sampleMetadata.hasWritePermission("test"));
    }

    @Test
    void addWritePermission() {
        sampleMetadata.addWritePermission("test");
        assertTrue(sampleMetadata.getWritableUsers().contains("test"));
    }

    @Test
    void removeWritePermission() {
        sampleMetadata.addWritePermission("test");
        sampleMetadata.removeWritePermission("test");
        assertFalse(sampleMetadata.getWritableUsers().contains("test"));
    }

    @Test
    void addReadPermission() {
        sampleMetadata.addReadPermission("test");
        assertTrue(sampleMetadata.getReadableUsers().contains("test"));
    }

    @Test
    void testAddReadPermission() {
        sampleMetadata.addReadPermission(Arrays.asList("test1", "test2"));
        assertTrue(sampleMetadata.getReadableUsers().containsAll(Arrays.asList("test1", "test2")));
    }
}