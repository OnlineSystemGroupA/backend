package com.stcos.server.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务层向 Controller 层抛出的异常
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/3 11:20
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class ServiceException extends Exception {

    /**
     * 异常状态码，根据不同的业务场景有不同的含义。
     * 在实现业务层接口时需要添加文档注释，解释当前业务场景下各异常状态码的含义。
     */
    int code;
}
