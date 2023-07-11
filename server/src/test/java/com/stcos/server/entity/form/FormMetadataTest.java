package com.stcos.server.entity.form;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import java.util.HashSet;
import java.util.Set;

class FormMetadataTest {

    private FormMetadata formMetadata;

    @BeforeEach
    void setUp() {
        formMetadata = new FormMetadata(1L, FormType.TYPE_TEST_REPORT_FORM);
    }

    @Test
    void hasReadPermission() {
        assertFalse(formMetadata.hasReadPermission("testUser1"));

        formMetadata.addReadPermission("testUser1");
        assertTrue(formMetadata.hasReadPermission("testUser1"));
    }

    @Test
    void hasWritePermission() {
        assertFalse(formMetadata.hasWritePermission("testUser1"));

        formMetadata.addWritePermission("testUser1");
        assertTrue(formMetadata.hasWritePermission("testUser1"));
    }

    @Test
    void addWritePermission() {
        assertFalse(formMetadata.hasWritePermission("testUser2"));

        formMetadata.addWritePermission("testUser2");
        assertTrue(formMetadata.hasWritePermission("testUser2"));
    }

    @Test
    void removeWritePermission() {
        formMetadata.addWritePermission("testUser3");
        formMetadata.addWritePermission("testUser3");
        assertTrue(formMetadata.hasWritePermission("testUser3"));

        formMetadata.removeWritePermission("testUser3");
        assertFalse(formMetadata.hasWritePermission("testUser3"));
    }

    @Test
    void addReadPermission() {
        assertFalse(formMetadata.hasReadPermission("testUser2"));

        formMetadata.addReadPermission("testUser2");
        assertTrue(formMetadata.hasReadPermission("testUser2"));
    }

    @Test
    void testAddReadPermission() {
        assertFalse(formMetadata.hasReadPermission("testUser3"));
        assertFalse(formMetadata.hasReadPermission("testUser4"));

        Set<String> users = new HashSet<>();
        users.add("testUser3");
        users.add("testUser4");

        formMetadata.addReadPermission(users);
        assertTrue(formMetadata.hasReadPermission("testUser3"));
        assertTrue(formMetadata.hasReadPermission("testUser4"));
    }
}
