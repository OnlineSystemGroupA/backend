package com.stcos.server.service.impl;

import com.stcos.server.exception.ServiceException;
import com.stcos.server.model.process.ProcessRecord;
import com.stcos.server.model.user.User;
import com.stcos.server.service.ArchiveService;
import com.stcos.server.service.ProcessRecordService;
import org.flowable.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ArchiveServiceImp implements ArchiveService {

    private RuntimeService runtimeService;

    @Autowired
    public void setRuntimeService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    private ProcessRecordService processRecordService;

    @Autowired
    void setProcessRecordService(ProcessRecordService processRecordService) {
        this.processRecordService = processRecordService;
    }

    public ProcessRecord getProcessRecord(String processId) {
//        if (!user.hasProcessInstance(processId)) throw new ServiceException(0);
        Long projectId = (Long) runtimeService.getVariable(processId, "projectId");

        return processRecordService.selectProcessRecordById(projectId);
    }
}
