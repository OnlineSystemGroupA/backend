package com.stcos.server.service.impl;

import com.stcos.server.entity.process.ProcessRecord;
import com.stcos.server.service.ProcessRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class ProcessRecordServiceImpTest {

    @Autowired
    private ProcessRecordService processRecordService;

    @Test
    void create() {
        Long id = processRecordService.create();
        ProcessRecord processRecord = processRecordService.getById(id);
        assertNotNull(processRecord, "Process record should not be null");
        assertEquals(id, processRecord.getId(), "Returned ID should match the record's ID");
    }
}
