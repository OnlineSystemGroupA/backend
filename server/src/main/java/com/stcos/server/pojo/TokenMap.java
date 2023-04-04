package com.stcos.server.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/4/4 10:37
 */

@Data
@AllArgsConstructor
@Schema(name = "TokenMap", description = "")
public class TokenMap {

    @Schema(description = "token 的头部，前端请求携带 token 时需要将其拼接在 token 之前")
    private String head;

    @Schema(description = "token 字符串")
    private String token;

}
