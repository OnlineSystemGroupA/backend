package com.stcos.server.exception;

/**
 * 当后端遇到无法恢复的错误，需要结束请求时，抛出该异常
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/17 14:27
 */
public class ServerErrorException extends RuntimeException {

    /**
     * 用指定的原因构造新的服务器错误异常。原因用来记录导致这个异常的底层错误
     *
     * @param cause 产生这个异常的原因
     */
    public ServerErrorException(Throwable cause) {
        super(cause);
    }
}
