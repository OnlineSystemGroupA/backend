package com.stcos.server.controller.api;

import com.stcos.server.pojo.LoginParam;
import com.stcos.server.pojo.RegisterParam;
import com.stcos.server.pojo.RespBean;
import com.stcos.server.pojo.TokenMap;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/2 18:03
 */
public interface LoginApi {

    @PostMapping("/register")
    @Operation(summary = "用户注册")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "600", description = "注册成功", content = @Content()),
            @ApiResponse(responseCode = "601", description = "用户名已存在", content = @Content()),
            @ApiResponse(responseCode = "602", description = "参数错误", content = @Content())
    })
    RespBean register(
            @Parameter(required = true)
            @Valid @RequestBody RegisterParam param,
            @Parameter(hidden = true)
            Errors errors);

    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "登录成功返回 token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "600", description = "登录成功",
                    content = @Content(schema = @Schema(implementation = TokenMap.class))),
            @ApiResponse(responseCode = "601", description = "用户不存在",
                    content = @Content()),
            @ApiResponse(responseCode = "602", description = "账号禁用",
                    content = @Content()),
            @ApiResponse(responseCode = "603", description = "用户名或密码错误",
                    content = @Content()),
            @ApiResponse(responseCode = "604", description = "参数错误",
                    content = @Content(schema = @Schema(implementation = Errors.class))),
    })
    RespBean login(
            @Parameter(required = true, description = "登录参数")
            @RequestBody
            @Valid LoginParam loginParam,
            @Parameter(hidden = true) Errors errors);

    @PostMapping("/logout")
    @Operation(summary = "注销登录")
    @ApiResponse(responseCode = "600", description = "注销成功", content = @Content())
    RespBean logout();
}
