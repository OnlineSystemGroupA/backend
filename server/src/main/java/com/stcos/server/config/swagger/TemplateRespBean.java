package com.stcos.server.config.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * 响应返回对象的泛型模板，只出现在 swagger 注解中，用于生成 API 文档
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/4/4 10:45
 */

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(name = "RespBean", description = "响应的返回对象")
public class TemplateRespBean<T> {

    @Schema(description = "状态码", name = "code")
    private long code;

    @Schema(description = "提示信息", name = "message")
    private String message;

    @Schema(description = "需要包含的其他信息", name = "obj")
    private T obj;

}
