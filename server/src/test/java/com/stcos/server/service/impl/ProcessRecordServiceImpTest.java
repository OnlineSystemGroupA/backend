package com.stcos.server.service.impl;

import com.stcos.server.model.file.SampleMetadata;
import com.stcos.server.model.form.FormMetadata;
import com.stcos.server.model.form.FormType;
import com.stcos.server.model.process.ProcessRecord;
import com.stcos.server.service.ProcessRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class ProcessRecordServiceImpTest {

    @Autowired
    private ProcessRecordService processRecordService;

    @Test
    void saveAndSelectProcessRecord() {

        Long projectId = 1234567890L;
        String clientId = "testClientId";
        String marketingManagerId = "testMarketingManagerId";
        String testingManagerId = "testTestingManagerId";
        String qualityManagerId = "testQualityManagerId";
        String signatoryId = "testSignatoryId";
        String marketingOperatorId = "testMarketingOperatorId";
        String testingOperatorId = "testTestingOperatorId";
        String startUserName = "testStartUserName";
        String title = "testTitle";
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime finishDate = LocalDateTime.now().plusDays(10);

        SampleMetadata sampleMetadata = new SampleMetadata();

        sampleMetadata.setSampleMetadataId(1234567890L);

        FormMetadata formMetadata1 = new FormMetadata(projectId, FormType.TYPE_TEST_REPORT_FORM);
        FormMetadata formMetadata2 = new FormMetadata(projectId, FormType.TYPE_APPLICATION_FORM);

        formMetadata1.addReadPermission("uid1");
        formMetadata1.addWritePermission("uid1");
        formMetadata2.addReadPermission("uid2");
        formMetadata2.addWritePermission("uid2");

        Map<String, Long> formMetadataIdMap = new HashMap<>();
//        List<FormMetadata> formMetadataSet = new ArrayList<>();
//        formMetadataSet.add(formMetadata1);
//        formMetadataSet.add(formMetadata2);

        ProcessRecord processRecord = new ProcessRecord(
                1234567890L,
                clientId,
                marketingManagerId,
                testingManagerId,
                qualityManagerId,
                signatoryId,
                marketingOperatorId,
                testingOperatorId,
                startUserName,
                title,
                startDate,
                finishDate,
                formMetadataIdMap,
                sampleMetadata
        );

        processRecordService.saveProcessRecord(processRecord);

        ProcessRecord retrievedProcessRecord = processRecordService.selectProcessRecordById(projectId);

        assertNotNull(retrievedProcessRecord);
        assertEquals(processRecord.getProjectId(), retrievedProcessRecord.getProjectId());
        assertEquals(processRecord.getFormMetadataIdMap(), retrievedProcessRecord.getFormMetadataIdMap());
        assertEquals(processRecord.getSampleMetadata(), retrievedProcessRecord.getSampleMetadata());
    }
}
