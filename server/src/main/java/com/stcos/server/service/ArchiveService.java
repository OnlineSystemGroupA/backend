package com.stcos.server.service;

import com.stcos.server.exception.ServiceException;
import com.stcos.server.model.process.ProcessRecord;
import org.springframework.http.ResponseEntity;

public interface ArchiveService {

    public ProcessRecord getProcessRecord(String processId);
}
