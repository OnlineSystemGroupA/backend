package com.stcos.server.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/3/30 17:43
 */

@Data
@Schema(name = "RegisterParam", description = "用户登录参数")
public class RegisterParam {

    @NotBlank(message = "用户名不能为空")
    @Schema(name = "username", description = "用户名，唯一")
    private String username;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "无效的邮箱地址")
    @Schema(name = "email", description = "用户邮箱")
    private String email;

    @Size(min = 8, max = 24, message = "密码长度必须在8到24之间")
    @Schema(name = "password", description = "用户密码")
    private String password;

    private String checkPass;

}
