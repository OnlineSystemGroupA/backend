package com.stcos.server.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/3/29 15:39
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true) // 便于链式操作
@Schema(title = "登录参数对象", name = "LoginParam")
public class LoginParam {

    @Schema(name = "用户名", requiredMode = REQUIRED)
    private String username;
    @Schema(name = "密码", requiredMode = REQUIRED)
    private String password;

}
