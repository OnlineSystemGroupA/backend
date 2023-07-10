package com.stcos.server.entity.form;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

class FormMetadataTest {

    private FormMetadata formMetadata;

    @BeforeEach
    void setUp() {
        formMetadata = new FormMetadata("ContractForm");
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

        List<String> users = new ArrayList<>();
        users.add("User3");
        users.add("User4");

        formMetadata.addReadPermission(users);

        assertTrue(formMetadata.hasReadPermission("User3"));
        assertTrue(formMetadata.hasReadPermission("User4"));
    }
}
