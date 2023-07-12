package com.stcos.server.listener;

import com.stcos.server.model.form.ContractForm;
import com.stcos.server.model.form.FormType;
import com.stcos.server.model.process.ProcessDetails;
import com.stcos.server.model.process.ProcessVariables;
import com.stcos.server.model.process.TaskName;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Locale;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/9 16:20
 */

@Component
public class StampContractListener extends OperatorTaskListener {
    public StampContractListener() {
        super(TaskName.NAME_TASK_18);
    }

    private final SimpleDateFormat sf = new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss", Locale.ENGLISH);

    @Override
    public void complete(DelegateTask task) {
        super.complete(task);
        Long metadataId = (Long) task.getVariable(FormType.TYPE_CONTRACT_FORM);
        ContractForm form = (ContractForm) formService.getForm(metadataId);
        // 更新流程详情
        Long projectId = (Long) task.getVariable(ProcessVariables.VAR_PROJECT_ID);
        ProcessDetails processDetails = processDetailsService.getById(projectId);
        try {
            processDetails.setStartDate(sf.parse(form.getSignDate().replace("GMT", "")
                    .replaceAll("\\(.*\\)", "")).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
//            processDetails.setDueDate(sf.parse(form.getValidDate().replace("GMT", "")
//                    .replaceAll("\\(.*\\)", "")).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
