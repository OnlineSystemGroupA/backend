package com.stcos.server.service.impl;

import com.stcos.server.database.mysql.ProcessDetailsMapper;
import com.stcos.server.entity.process.ProcessDetails;
import com.stcos.server.entity.process.TaskDetails;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.service.ProcessDetailsService;
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
public class ProcessDetailsServiceImpTest {

    @Autowired
    private ProcessDetailsService processDetailsService;

    @Autowired
    private ProcessDetailsMapper processDetailsMapper;

    @Test
    void openTask() {
        Long projectId = processDetailsService.create();

        String taskName = "taskName";
        processDetailsService.openTask(projectId, taskName, "userName");

        ProcessDetails processDetails = processDetailsMapper.selectProcessDetails(projectId);
        // Check if the task is opened
        assertEquals(taskName, processDetails.getCurrentTaskName());
    }

    @Test
    void closeTask() {
        Long projectId = processDetailsService.create();

        String taskName = "taskName";
        processDetailsService.openTask(projectId, taskName, "userName");
        processDetailsService.closeTask(projectId, taskName);

        ProcessDetails processDetails = processDetailsMapper.selectProcessDetails(projectId);
        // Check if the task is closed
        assertNotNull(processDetails.getTaskDetailsList().get(processDetails.getTaskDetailsList().size() - 1).getFinishDate());
    }

    @Test
    void update() {
        Long projectId = processDetailsService.create();

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

        ProcessDetails processDetails = processDetailsMapper.selectById(projectId);
        // Check if the processDetails is updated
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
        Long projectId = processDetailsService.create();

        ProcessDetails processDetails = processDetailsMapper.selectById(projectId);
        // Check if the processDetails is created
        assertNotNull(processDetails);
    }
}
