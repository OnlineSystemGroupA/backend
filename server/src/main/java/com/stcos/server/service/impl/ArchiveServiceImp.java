package com.stcos.server.service.impl;

import com.stcos.server.exception.ServiceException;
import com.stcos.server.model.dto.FormInfoDto;
import com.stcos.server.model.form.Form;
import com.stcos.server.model.process.ProcessDetails;
import com.stcos.server.model.process.ProcessRecord;
import com.stcos.server.model.user.Admin;
import com.stcos.server.model.user.User;
import com.stcos.server.service.ArchiveService;
import com.stcos.server.service.FormService;
import com.stcos.server.service.ProcessDetailsService;
import com.stcos.server.service.ProcessRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArchiveServiceImp implements ArchiveService {

    private ProcessRecordService processRecordService;

    @Autowired
    void setProcessRecordService(ProcessRecordService processRecordService) {
        this.processRecordService = processRecordService;
    }

    private ProcessDetailsService processDetailsService;

    @Autowired
    public void setProcessDetailsService(ProcessDetailsService processDetailsService) {
        this.processDetailsService = processDetailsService;
    }

    private FormService formService;

    @Autowired
    public void setFormService(FormService formService) {
        this.formService = formService;
    }

    private final Map<String, Comparator<ProcessRecord>> comparatorMap = new HashMap<>() {{
        put("projectId", Comparator.comparing(ProcessRecord::getProjectId));
        put("title", Comparator.comparing(ProcessRecord::getTitle));
        put("startDate", Comparator.comparing(ProcessRecord::getStartDate));
    }};

    @Override
    public Integer getProcessCount() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getProcessRecordCount();
    }

    @Override
    public ProcessDetails getProcessDetails(Long projectId) {
        return processDetailsService.getById(projectId);
    }

    @Override
    public List<FormInfoDto> getFormInfo(Long projectId) {
        List<FormInfoDto> formInfoDtoList = new ArrayList<>();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ProcessRecord processRecord = processRecordService.selectProcessRecordById(projectId);
        Map<String, Long> formMetadataIdMap = processRecord.getFormMetadataIdMap();
        if (user instanceof Admin) {
            for (String formType : formMetadataIdMap.keySet()) {
                Long metadataId = formMetadataIdMap.get(formType);
                String createdDate = formService.getCreatedDate(metadataId).toString();
                String formState = formService.getFormState(metadataId);
                formInfoDtoList.add(new FormInfoDto(formType, createdDate, formState));
            }
        } else {
            if (user.hasProcessRecord(String.valueOf(projectId))) {
                for (String formType : formMetadataIdMap.keySet()) {
                    Long metadataId = formMetadataIdMap.get(formType);
                    if (formService.hasReadPermission(metadataId, user.getUid())) {
                        String createdDate = formService.getCreatedDate(metadataId).toString();
                        String formState = formService.getFormState(metadataId);
                        formInfoDtoList.add(new FormInfoDto(formType, createdDate, formState));
                    }
                }
            }
        }
        return formInfoDtoList;
    }

    @Override
    public Form getForm(Long projectId, String formType) throws ServiceException {
        ProcessRecord processRecord = processRecordService.selectProcessRecordById(projectId);

        // 判断 projectId 对应的流程是否存在，并获取表单元数据 ID
        Map<String, Long> formMetadataIdMap = processRecord.getFormMetadataIdMap();
        Long formMetadataId = formMetadataIdMap.get(formType);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 调用 FormService 接口，返回表单对象
        return formService.getForm(formMetadataId, user.getUid());
    }

    @Override
    public List<ProcessRecord> getProcessRecords(Integer pageIndex, Integer numPerPage, String orderBy, Boolean assigned) throws ServiceException {
        List<ProcessRecord> processRecordList = new ArrayList<>();

        // 判断待排序的键是否有效
        if (!comparatorMap.containsKey(orderBy)) throw new ServiceException(0);

        // 查找当前登录用户可见的流程记录
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Long> processRecordIdList = user.getProcessRecords();
        if (processRecordIdList.isEmpty()) return processRecordList;
        for (Long aLong : processRecordIdList) {
            processRecordList.add(processRecordService.selectProcessRecordById(aLong));
        }

        // 根据对应字段对流程实例进行排序，并截取部分内容
        processRecordList.sort(comparatorMap.get(orderBy));

        // 生成索引
        int beginIndex = numPerPage * (pageIndex - 1);
        int toIndex = beginIndex + numPerPage;
        if (beginIndex >= processRecordList.size()) return processRecordList;
        if (toIndex >= processRecordList.size()) toIndex = processRecordList.size();

        return processRecordList.subList(beginIndex, toIndex);
    }
}
