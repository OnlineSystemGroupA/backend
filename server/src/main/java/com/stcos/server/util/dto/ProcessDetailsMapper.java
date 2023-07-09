package com.stcos.server.util.dto;

import com.stcos.server.entity.dto.ProcessDetailsDto;
import com.stcos.server.entity.process.ProcessDetails;
import lombok.experimental.UtilityClass;

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
     *
     * @param processDetails
     * @param currentTaskName
     * @param index
     * @return
     */
    public ProcessDetailsDto toProcessDetailsDto(ProcessDetails processDetails, String currentTaskName, Integer index) {
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
                index,
                currentTaskName
        );
    }

}
