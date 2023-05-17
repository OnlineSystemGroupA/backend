package com.stcos.server.exception;

/**
 * 后端遇到无法恢复，需要结束请求的异常时抛出
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/17 14:27
 */
public class ServerErrorException extends RuntimeException {

    public ServerErrorException(Throwable cause) {
        super(cause);
    }

}
