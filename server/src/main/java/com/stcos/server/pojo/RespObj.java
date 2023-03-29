package com.stcos.server.pojo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应的返回对象
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/3/29 15:30
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RespObj {

    /**
     * 状态码
     */
    private long code;
    /**
     * 提示语
     */
    private String message;
    /**
     * 需要包含的其他信息
     */
    private Object obj;

    public static RespObj success(String message) {
        return new RespObj(200, message, null);
    }

    public static RespObj success(String message, Object obj) {
        return new RespObj(200, message, obj);
    }

    public static RespObj error(String message) {
        return new RespObj(500, message, null);
    }

    public static RespObj error(String message, Object obj) {
        return new RespObj(500, message, obj);
    }

}
