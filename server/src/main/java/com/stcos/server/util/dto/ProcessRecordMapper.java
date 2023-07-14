package com.stcos.server.util.dto;

import com.stcos.server.model.dto.FormMetadataDto;
import com.stcos.server.model.dto.ProcessRecordDto;
import com.stcos.server.model.dto.SampleMetadataDto;
import com.stcos.server.model.file.SampleMetadata;
import com.stcos.server.model.form.FormMetadata;
import com.stcos.server.model.process.ProcessRecord;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ProcessRecordMapper {

    public ProcessRecordDto toProcessRecordDto(ProcessRecord processRecord) {
        // convert main process record fields
        ProcessRecordDto processRecordDto = new ProcessRecordDto();
        processRecordDto.setProjectId(processRecord.getProjectId());
        processRecordDto.setClientId(processRecord.getClientId());
        processRecordDto.setMarketDepartmentEmployeeId(processRecord.getMarketingManagerId());
        processRecordDto.setTestingEmployeeId(processRecord.getTestingManagerId());
        processRecordDto.setQualitySupervisorId(processRecord.getQualityManagerId());
        processRecordDto.setSignatoryId(processRecord.getSignatoryId());
        processRecordDto.setMarketDepartmentSupervisorId(processRecord.getMarketingOperatorId());
        processRecordDto.setTestingEmployeeId(processRecord.getTestingOperatorId());
        processRecordDto.setStartUserName(processRecord.getStartUserName());
        processRecordDto.setTitle(processRecord.getTitle());
        processRecordDto.setStartDate(processRecord.getStartDate().toString());
        processRecordDto.setFinishDate(processRecord.getFinishDate().toString());

        // convert formMetadataList
        List<FormMetadataDto> formMetadataDtoList = new ArrayList<>();
        for (FormMetadata formMetadata : processRecord.getFormMetadataList()) {
            FormMetadataDto formMetadataDto = new FormMetadataDto(formMetadata.getFormMetadataId(), formMetadata.getFormType());
            formMetadataDtoList.add(formMetadataDto);
        }
        processRecordDto.setFormMetadataList(formMetadataDtoList);

        // convert sampleMetadata
        SampleMetadata sampleMetadata = processRecord.getSampleMetadata();
        SampleMetadataDto sampleMetadataDto = new SampleMetadataDto(sampleMetadata.getSampleMetadataId());
        sampleMetadataDto.setReadableUsers(sampleMetadata.getReadableUsers());
        sampleMetadataDto.setWritableUsers(sampleMetadata.getWritableUsers());
        sampleMetadataDto.setFileMetadataIdList(sampleMetadata.getFileMetadataIdList());
        processRecordDto.setSampleMetadata(sampleMetadataDto);

        return processRecordDto;
    }
}
