package com.stcos.server.service.impl;

import com.stcos.server.exception.ServiceException;
import com.stcos.server.model.file.FileMetadata;
import com.stcos.server.model.file.SampleMetadata;
import com.stcos.server.model.form.ContractForm;
import com.stcos.server.model.form.FormType;
import com.stcos.server.model.user.Client;
import com.stcos.server.service.FileMetadataService;
import com.stcos.server.service.FormMetadataService;
import com.stcos.server.service.SampleMetadataService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
@Rollback
class WorkflowServiceImpTest {

    @Autowired
    private WorkflowServiceImp workflowServiceImp;

    private final String testProcessId = "testProcessId";

    @Autowired
    private FormMetadataService formMetadataService;

    Long formMetadataId = null;

    ContractForm form = null;

    Client mockUser = null;

    String formType = FormType.TYPE_CONTRACT_FORM;

    @MockBean
    private RuntimeService runtimeService;

    @Mock
    private ProcessInstanceQuery processInstanceQuery;

    @BeforeEach
    void setUpFormPart() {

        // Create a User object
        mockUser = new Client("testUser", "testPassword", "testEmail@test.com");
        mockUser.setUid("testUid");

        // Prepare test data
        formMetadataId = formMetadataService.create(1234567890L, formType);
        formMetadataService.addWritePermission(formMetadataId, mockUser.getUid());
        formMetadataService.addReadPermission(formMetadataId, mockUser.getUid());
        form = new ContractForm();
        form.setClient("testClient");

        // Create a mock Authentication object
        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(mockUser);

        // Create a mock SecurityContext object
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        // Set the mock SecurityContext object to SecurityContextHolder
        SecurityContextHolder.setContext(securityContext);

        // Create a mock ProcessInstance object
        processInstanceQuery = Mockito.mock(ProcessInstanceQuery.class);
        Mockito.when(runtimeService.createProcessInstanceQuery()).thenReturn(processInstanceQuery);
        Mockito.when(processInstanceQuery.processInstanceId(anyString())).thenReturn(processInstanceQuery);
        Mockito.when(processInstanceQuery.includeProcessVariables()).thenReturn(processInstanceQuery);
        Mockito.when(processInstanceQuery.singleResult()).thenReturn(Mockito.mock(ProcessInstance.class));

        when(runtimeService.getVariable(testProcessId, formType)).thenReturn(formMetadataId);
    }

    @Test
    void updateFormWithInvalidProcessId() {
        Mockito.when(processInstanceQuery.singleResult()).thenReturn(null);
        ServiceException exception = assertThrows(ServiceException.class, () -> workflowServiceImp.updateForm(testProcessId, formType, form));
        assertEquals(0, exception.getCode());
    }

    @Test
    void updateAndGetFormSuccessful() throws ServiceException {
        workflowServiceImp.updateForm(testProcessId, formType, form);

        ContractForm retrievedForm = (ContractForm) workflowServiceImp.getForm(testProcessId, formType);
        assertEquals(form.getClient(), retrievedForm.getClient());
    }

    @Autowired
    private SampleMetadataService sampleMetadataService;

    @Autowired
    private FileMetadataService fileMetadataService;

    private Long sampleMetadataId = null;

    private MockMultipartFile mockFile = null;

    @BeforeEach
    void setUpFilePart() throws IOException {
        sampleMetadataId = sampleMetadataService.create();
        SampleMetadata sampleMetadata = sampleMetadataService.getById(sampleMetadataId);
        sampleMetadata.addWritePermission(mockUser.getUid());
        sampleMetadata.addReadPermission(mockUser.getUid());
        sampleMetadataService.updateById(sampleMetadata);

        // Prepare a mock file
        mockFile = new MockMultipartFile("test.txt", "test.txt", "text/plain", "test data".getBytes());

        when(runtimeService.getVariable(testProcessId, "sampleMetadata")).thenReturn(sampleMetadataId);
    }

    @Test
    void uploadSampleWithInvalidProcessId() {
        Mockito.when(processInstanceQuery.singleResult()).thenReturn(null);

        ServiceException exception = assertThrows(ServiceException.class, () -> workflowServiceImp.uploadSample(testProcessId, mockFile));
        assertEquals(0, exception.getCode());
    }

    @Test
    void uploadAndDownloadAndDeleteSampleSuccessful() throws ServiceException, IOException {
        FileMetadata uploadedFileMetadata = workflowServiceImp.uploadSample(testProcessId, mockFile);
        assertEquals(mockFile.getName(), uploadedFileMetadata.getFileName());

        workflowServiceImp.uploadSample(testProcessId, mockFile);

        assertDoesNotThrow(() -> workflowServiceImp.downloadSample(testProcessId));

        // Download the sample
        File zipFile = assertDoesNotThrow(() -> workflowServiceImp.downloadSample(testProcessId));

        // Prepare the directory to unzip the file
        Path unzipDir = Files.createTempDirectory("unzip");
        try (ZipFile zip = new ZipFile(zipFile)) {
            // Extract all entries
            for (ZipEntry entry : Collections.list(zip.entries())) {
                Path path = unzipDir.resolve(entry.getName());
                Files.copy(zip.getInputStream(entry), path, StandardCopyOption.REPLACE_EXISTING);
            }
        }

        List<String> fileNames = Files.list(unzipDir)
                .map(Path::getFileName)
                .map(Path::toString)
                .toList();
        assertEquals(2, fileNames.size());

        SampleMetadata retrievedSampleMetadata = sampleMetadataService.getById(sampleMetadataId);
        assertEquals(2, retrievedSampleMetadata.getFileMetadataIdList().size());

        for (Long fileMetadataId : retrievedSampleMetadata.getFileMetadataIdList()) {
            FileMetadata metadata = fileMetadataService.getById(fileMetadataId);
            assertTrue(fileNames.contains(metadata.getFileName()));
        }

        assertDoesNotThrow(() -> workflowServiceImp.deleteSample(testProcessId));
    }
}
