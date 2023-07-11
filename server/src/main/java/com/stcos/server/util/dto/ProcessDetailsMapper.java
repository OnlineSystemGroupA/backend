package com.stcos.server.util.dto;

import com.stcos.server.entity.dto.ProcessDetailsDto;
import com.stcos.server.entity.process.ProcessDetails;
import com.stcos.server.util.TaskUtil;
import lombok.experimental.UtilityClass;
import org.flowable.task.api.Task;

import java.time.LocalDateTime;

import static com.stcos.server.entity.process.ProcessVariables.VAR_ASSIGNEE;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/7/9 16:37
 */

@UtilityClass
public class ProcessDetailsMapper {

    /**
     * @param processDetails
     * @param task
     * @return
     */
    public ProcessDetailsDto toProcessDetailsDto(ProcessDetails processDetails, Task task) {
        String currentTaskName = task.getName();
        String assignee = (String) task.getProcessVariables().get(VAR_ASSIGNEE);
        int index = TaskUtil.getTaskGroupIndex(currentTaskName);

        LocalDateTime startDate = processDetails.getStartDate();
        LocalDateTime dueDate = processDetails.getDueDate();

        String startDateStr = null, dueDateStr = null;
        if (startDate != null) startDateStr = startDate.toString();
        if (dueDate != null) dueDateStr = dueDate.toString();

        return new ProcessDetailsDto(
                processDetails.getProjectId(),
                processDetails.getTitle(),
                processDetails.getVersion(),
                processDetails.getTestType(),
                processDetails.getApplicationDate(),
                processDetails.getApplicant(),
                processDetails.getCompany(),
                processDetails.getTelephone(),
                processDetails.getEmail(),
                processDetails.getAddress(),
                startDateStr,
                dueDateStr,
                index,
                currentTaskName,
                assignee
        );
    }

}
