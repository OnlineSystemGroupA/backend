package com.stcos.server.util.dto;

import com.stcos.server.model.dto.OperatorDetailsDto;
import com.stcos.server.model.user.Operator;
import lombok.experimental.UtilityClass;

/**
 * 这是一个映射工具类，主要用于将 Operator 对象映射为 OperatorDetailsDto 对象
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/7/9 21:01
 */

@UtilityClass
public class OperatorDetailsMapper {

    /**
     * 将 Operator 对象映射为 OperatorDetailsDto 对象
     *
     * @param operator 要被映射的 Operator 对象
     * @return 映射后的 OperatorDetailsDto 对象
     */
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
