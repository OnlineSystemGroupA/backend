package com.stcos.server.service;

import com.stcos.server.exception.ServiceException;
import com.stcos.server.model.dto.FormInfoDto;
import com.stcos.server.model.form.Form;
import com.stcos.server.model.process.ProcessDetails;
import com.stcos.server.model.process.ProcessRecord;

import java.util.List;

public interface ArchiveService {

    public ProcessRecord getProcessRecord(String processId);

    List<ProcessRecord> getProcessRecords(Integer pageIndex, Integer numPerPage, String orderBy, Boolean assigned) throws ServiceException;

    Integer getProcessCount();

    ProcessDetails getProcessDetails(Long projectId);

    List<FormInfoDto> getFormInfo(Long projectId);

    Form getForm(Long projectId, String formName) throws ServiceException;
}
