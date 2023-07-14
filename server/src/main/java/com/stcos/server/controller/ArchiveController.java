package com.stcos.server.controller;

import com.stcos.server.controller.api.ArchiveApi;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.model.dto.FormInfoDto;
import com.stcos.server.model.dto.ProcessDetailsDto;
import com.stcos.server.model.dto.ProcessDto;
import com.stcos.server.model.dto.ProcessRecordDto;
import com.stcos.server.model.form.Form;
import com.stcos.server.model.process.ProcessDetails;
import com.stcos.server.model.process.ProcessRecord;
import com.stcos.server.service.ArchiveService;
import com.stcos.server.util.JSONUtil;
import com.stcos.server.util.dto.ProcessDetailsMapper;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public ResponseEntity<Integer> getProcessRecordCount() {
        return ResponseEntity.ok(archiveService.getProcessCount());
    }

    @Override
    public ResponseEntity<List<ProcessDto>> getArchivedProcesses(Integer pageIndex, Integer numPerPage, String orderBy, Boolean assigned) {
        List<ProcessRecord> processRecordList;
        try {
            processRecordList = archiveService.getProcessRecords(pageIndex, numPerPage, orderBy, assigned);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
        List<ProcessDto> processDtoList = new ArrayList<>(processRecordList.size());

        //  ProcessDto
        for (ProcessRecord processRecord : processRecordList) {
            processDtoList.add(
                    new ProcessDto(((Long) processRecord.getProjectId()).toString(),
                            null,
                            null,
                            processRecord.getTitle(),
                            null,
                            null,
                            processRecord.getStartUserName(),
                            processRecord.getStartDate().toString()
                    )
            );
        }
        return ResponseEntity.ok(processDtoList);
    }

    @Override
    public ResponseEntity<ProcessDetailsDto> getProcessRecord(Long projectId) {
        ProcessDetails processDetails = archiveService.getProcessDetails(projectId);
        ProcessDetailsDto processDetailsDto = ProcessDetailsMapper.toProcessDetailsDto(processDetails);
        return ResponseEntity.ok(processDetailsDto);
    }

    @Override
    public ResponseEntity<List<FormInfoDto>> getArchivedFormInfo(Long projectId) {
        ResponseEntity<List<FormInfoDto>> result;
        result = ResponseEntity.ok(archiveService.getFormInfo(projectId));
        return result;
    }

    @Override
    public ResponseEntity<String> getArchivedForm(Long projectId, String formName) {
        ResponseEntity<String> response;
        try {
            Form form = archiveService.getForm(projectId, formName);
            String formData = JSONUtil.toJSONString(form);
            response = ResponseEntity.ok(formData);
        } catch (ServiceException e) {
            switch (e.getCode()) {
                case 0 -> response = ResponseEntity.status(403).build();   // 指定流程或表单对该用户不可见
                case 1 -> response = ResponseEntity.status(404).build();   // 指定流程或表单不存在
                default -> response = ResponseEntity.internalServerError().build();
            }
        }
        return response;
    }


}
