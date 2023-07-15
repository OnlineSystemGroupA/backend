package com.stcos.server.listener;

import com.stcos.server.database.mysql.FormMetadataMapper;
import com.stcos.server.database.mysql.SampleMetadataMapper;
import com.stcos.server.model.file.SampleMetadata;
import com.stcos.server.model.form.Form;
import com.stcos.server.model.form.FormMetadata;
import com.stcos.server.model.form.FormType;
import com.stcos.server.model.process.ProcessRecord;
import com.stcos.server.model.process.ProcessVariables;
import com.stcos.server.model.process.TaskName;
import com.stcos.server.model.user.Client;
import com.stcos.server.model.user.User;
import com.stcos.server.service.EmailService;
import com.stcos.server.service.ProcessRecordService;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/9 15:59
 */
@Component
public class ConfirmTestReportListener extends ClientTaskListener {

    public ConfirmTestReportListener() {
        super(TaskName.NAME_TASK_33);
    }

    private ProcessRecordService processRecordService;

    private SampleMetadataMapper sampleMetadataMapper;

    @Autowired
    public void setProcessRecordService(ProcessRecordService processRecordService) {
        this.processRecordService = processRecordService;
    }

    @Autowired
    public void setSampleMetadataMapper(SampleMetadataMapper sampleMetadataMapper) {
        this.sampleMetadataMapper = sampleMetadataMapper;
    }

    @Override
    public void complete(DelegateTask task) {
        super.complete(task);
        ProcessRecord processRecord = new ProcessRecord();
        processRecord.setProjectId((Long) task.getVariable(ProcessVariables.VAR_PROJECT_ID));
        processRecord.setClientId((String) task.getVariable(ProcessVariables.VAR_CLIENT));
        processRecord.setMarketingManagerId((String) task.getVariable(ProcessVariables.VAR_MARKETING_MANAGER));
        processRecord.setTestingManagerId((String) task.getVariable(ProcessVariables.VAR_TESTING_MANAGER));
        processRecord.setQualityManagerId((String) task.getVariable(ProcessVariables.VAR_QUALITY_MANAGER));
        processRecord.setSignatoryId((String) task.getVariable(ProcessVariables.VAR_SIGNATORY));
        processRecord.setMarketingOperatorId((String) task.getVariable(ProcessVariables.VAR_MARKETING_OPERATOR));
        processRecord.setTestingOperatorId((String) task.getVariable(ProcessVariables.VAR_TESTING_OPERATOR));
        processRecord.setStartUserName((String) task.getVariable(ProcessVariables.VAR_START_USER));
        processRecord.setTitle((String) task.getVariable(ProcessVariables.VAR_TITLE));
        processRecord.setStartDate((LocalDateTime) task.getVariable(ProcessVariables.VAR_START_DATE));
        processRecord.setFinishDate(LocalDateTime.now());
        processRecord.setSampleMetadata(sampleMetadataMapper
                .selectById((Long) task
                        .getVariable(ProcessVariables.VAR_SAMPLE_METADATA)));
        Map<String, Long> map = new HashMap<>();
        for (String formType : FormType.FORM_TYPE_SET) {
            map.put(formType, (Long) task.getVariable(formType));
        }
        processRecord.setFormMetadataIdMap(map);
        processRecordService.saveProcessRecord(processRecord);
        Client user = userService.getById(task.getAssignee());
        user.addProcessRecord(processRecord.getProjectId());
        // 执行归档操作
        userService.updateById(user);
    }
}
