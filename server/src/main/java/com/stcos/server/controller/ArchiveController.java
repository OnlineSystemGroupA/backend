package com.stcos.server.controller;

import com.stcos.server.controller.api.ArchiveApi;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.model.dto.ProcessDetailsDto;
import com.stcos.server.model.dto.ProcessDto;
import com.stcos.server.model.form.Form;
import com.stcos.server.model.process.ProcessDetails;
import com.stcos.server.model.process.ProcessRecord;
import com.stcos.server.model.dto.ProcessRecordDto;
import com.stcos.server.service.ArchiveService;
import com.stcos.server.util.dto.ProcessRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/10 18:22
 */

@RestController
public class ArchiveController implements ArchiveApi {

    private ArchiveService archiveService;

    @Autowired
    void setArchiveService(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }

    @Override
    public ResponseEntity<List<ProcessDto>> getArchiveProcesses(Integer pageIndex, Integer numPerPage, String orderBy, Boolean assigned) {
        return ArchiveApi.super.getArchiveProcesses(pageIndex, numPerPage, orderBy, assigned);
    }

    @Override
    public ResponseEntity<Void> deleteArchiveProcess(String processId) {
        return ArchiveApi.super.deleteArchiveProcess(processId);
    }

    @Override
    public ResponseEntity<ProcessRecordDto> getProcessRecord(String processId) {
        ProcessRecord processRecord = null;
//        try {
            processRecord = archiveService.getProcessRecord(processId);
            ProcessRecordDto processRecordDto = ProcessRecordMapper.toProcessRecordDto(processRecord);
//            return ResponseEntity.ok(processRecordDto);
//        } catch (ServiceException e) {
//            if (e.getCode() == 0) return ResponseEntity.status(404).build();
//        }
        return ResponseEntity.internalServerError().build();
    }
}
