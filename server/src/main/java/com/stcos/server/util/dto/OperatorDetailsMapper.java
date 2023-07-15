package com.stcos.server.util.dto;

import com.stcos.server.model.dto.OperatorDetailsDto;
import com.stcos.server.model.user.Operator;
import lombok.experimental.UtilityClass;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/7/9 21:01
 */
@UtilityClass
public class OperatorDetailsMapper {

    public OperatorDetailsDto toOperatorDetailsDto(Operator operator) {
        return new OperatorDetailsDto(
                operator.getUid(),
                operator.getJobNumber(),
                operator.getEmail(),
                operator.getPhone(),
                operator.getRealName(),
                operator.getDepartment(),
                operator.getPosition(),
                operator.isAccountNonLocked()
        );
    }

}
