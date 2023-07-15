package com.stcos.server.util.dto;

import com.stcos.server.model.dto.ProcessDetailsDto;
import com.stcos.server.model.process.ProcessDetails;
import com.stcos.server.util.TaskUtil;
import lombok.experimental.UtilityClass;
import org.flowable.task.api.Task;

import java.time.LocalDateTime;

import static com.stcos.server.model.process.ProcessVariables.VAR_ASSIGNEE;

/**
 * 这是一个映射工具类，主要用于将 ProcessDetails 对象映射为 ProcessDetailsDto 对象
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/7/9 16:37
 */

@UtilityClass
public class ProcessDetailsMapper {

    /**
     * 将 ProcessDetails 和 Task 对象映射为 ProcessDetailsDto 对象
     *
     * @param processDetails 要被映射的 ProcessDetails 对象
     * @param task 要被映射的 Task 对象
     * @return 映射后的 ProcessDetailsDto 对象
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

    /**
     * 将 ProcessDetails 对象映射为 ProcessDetailsDto 对象
     *
     * @param processDetails 要被映射的 ProcessDetails 对象
     * @return 映射后的 ProcessDetailsDto 对象
     */
    public static ProcessDetailsDto toProcessDetailsDto(ProcessDetails processDetails) {

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
                processDetails.getStartDate().toString(),
                processDetails.getDueDate().toString(),
                null,
                null,
                null
        );
    }
}
