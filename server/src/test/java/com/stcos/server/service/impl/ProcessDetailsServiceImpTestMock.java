package com.stcos.server.service.impl;

import com.stcos.server.database.mysql.ProcessDetailsMapper;
import com.stcos.server.entity.process.ProcessDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProcessDetailsServiceImpTestMock {

    @InjectMocks
    private ProcessDetailsServiceImp processDetailsService;

    @Mock
    private ProcessDetailsMapper processDetailsMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void openTask() {
        Long projectId = 1L;
        ProcessDetails processDetails = new ProcessDetails();
        when(processDetailsMapper.selectProcessDetails(projectId)).thenReturn(processDetails);

        String taskName = "taskName";
        processDetailsService.openTask(projectId, taskName, "userName");

        assertEquals(taskName, processDetails.getCurrentTaskName());
        verify(processDetailsMapper, times(1)).saveProcessDetails(processDetails);
    }

    @Test
    void closeTask() {
        Long projectId = 1L;
        ProcessDetails processDetails = new ProcessDetails();
        when(processDetailsMapper.selectProcessDetails(projectId)).thenReturn(processDetails);

        String taskName = "taskName";
        processDetailsService.openTask(projectId, taskName, "userName");
        processDetailsService.closeTask(projectId, taskName);

        assertNotNull(processDetails.getTaskDetailsList().get(processDetails.getTaskDetailsList().size() - 1).getFinishDate());
    }

    @Test
    void update() {
        Long projectId = 1L;
        ProcessDetails processDetails = new ProcessDetails();
        when(processDetailsMapper.selectProcessDetails(projectId)).thenReturn(processDetails);

        List<String> testTypes = Arrays.asList("type1", "type2");
        String softwareName = "softwareName";
        String softwareVersion = "softwareVersion";
        String startDate = "startDate";
        String companyChineseName = "companyChineseName";
        String email = "email";
        String address = "address";
        String startUser = "startUser";
        String telephone = "telephone";

        processDetailsService.update(projectId, softwareName, softwareVersion, testTypes, startDate,
                companyChineseName, email, address, startUser, telephone);

        assertEquals(softwareName, processDetails.getTitle());
        assertEquals(softwareVersion, processDetails.getVersion());
        assertEquals(testTypes.toString(), processDetails.getTestType());
        assertEquals(startDate, processDetails.getApplicationDate());
        assertEquals(companyChineseName, processDetails.getCompany());
        assertEquals(email, processDetails.getEmail());
        assertEquals(address, processDetails.getAddress());
        assertEquals(startUser, processDetails.getApplicant());
        assertEquals(telephone, processDetails.getTelephone());
    }

    @Test
    void create() {
        ProcessDetails processDetails = new ProcessDetails();

        Long projectId = processDetailsService.create();

        assertNull(projectId);
    }
}
