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
        assertFalse(formMetadata.hasReadPermission("User1"));

        formMetadata.addReadPermission("User1");

        assertTrue(formMetadata.hasReadPermission("User1"));
    }

    @Test
    void hasWritePermission() {
        assertFalse(formMetadata.hasWritePermission("User1"));

        formMetadata.addWritePermission("User1");

        assertTrue(formMetadata.hasWritePermission("User1"));
    }

    @Test
    void addWritePermission() {
        assertFalse(formMetadata.hasWritePermission("User2"));

        formMetadata.addWritePermission("User2");

        assertTrue(formMetadata.hasWritePermission("User2"));
    }

    @Test
    void removeWritePermission() {
        formMetadata.addWritePermission("User3");
        formMetadata.addWritePermission("User3");

        assertTrue(formMetadata.hasWritePermission("User3"));

        formMetadata.removeWritePermission("User3");

        assertFalse(formMetadata.hasWritePermission("User3"));
    }

    @Test
    void addReadPermission() {
        assertFalse(formMetadata.hasReadPermission("User2"));

        formMetadata.addReadPermission("User2");

        assertTrue(formMetadata.hasReadPermission("User2"));
    }

    @Test
    void testAddReadPermission() {
        assertFalse(formMetadata.hasReadPermission("User3"));
        assertFalse(formMetadata.hasReadPermission("User4"));

        Set<String> users = new HashSet<>();
        users.add("User3");
        users.add("User4");

        formMetadata.addReadPermission(users);

        assertTrue(formMetadata.hasReadPermission("User3"));
        assertTrue(formMetadata.hasReadPermission("User4"));
    }
}
