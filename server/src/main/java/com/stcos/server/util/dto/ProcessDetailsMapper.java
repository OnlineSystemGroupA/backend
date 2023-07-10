package com.stcos.server.util.dto;

import com.stcos.server.entity.dto.ProcessDetailsDto;
import com.stcos.server.entity.process.ProcessDetails;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

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
     * @param currentTaskName
     * @param index
     * @return
     */
    public ProcessDetailsDto toProcessDetailsDto(ProcessDetails processDetails, String currentTaskName, Integer index) {
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
                currentTaskName
        );
    }

}
