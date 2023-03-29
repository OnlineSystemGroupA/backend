package com.stcos.server.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "状态码", name = "code")
    private long code;

    @Schema(description = "提示信息", name = "message")
    private String message;

    @Schema(description = "需要包含的其他信息", name = "obj")
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
