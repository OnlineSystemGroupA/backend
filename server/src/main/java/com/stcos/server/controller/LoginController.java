package com.stcos.server.controller;

import com.stcos.server.pojo.LoginParam;
import com.stcos.server.pojo.RegisterParam;
import com.stcos.server.pojo.RespBean;
import com.stcos.server.pojo.TokenMap;
import com.stcos.server.service.UserAuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/*
            __                _       ______            __             ____
           / /   ____  ____ _(_)___  / ____/___  ____  / /__________  / / /__  _____
          / /   / __ \/ __ `/ / __ \/ /   / __ \/ __ \/ __/ ___/ __ \/ / / _ \/ ___/
         / /___/ /_/ / /_/ / / / / / /___/ /_/ / / / / /_/ /  / /_/ / / /  __/ /
        /_____/\____/\__, /_/_/ /_/\____/\____/_/ /_/\__/_/   \____/_/_/\___/_/
                    /____/
 */

/**
 * 提供用户认证的接口
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/3/29 15:27
 */

@Slf4j
@RestController
@Tag(name = "LoginController")
public class LoginController {

    private UserAuthenticationService service;

//////////////////////////////////// AUTO_WIRED BEGIN ///////////////////////////////////////
    @Autowired
    public void setPosService(UserAuthenticationService service) {
        this.service = service;
    }
//////////////////////////////////// AUTO_WIRED END   ///////////////////////////////////////


    @PostMapping("/register")
    @Operation(summary = "用户注册")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "600", description = "注册成功", content = @Content()),
            @ApiResponse(responseCode = "601", description = "用户名已存在", content = @Content()),
            @ApiResponse(responseCode = "602", description = "参数错误", content = @Content())
    })
    public RespBean register(
            @Parameter(required = true)
            @Valid @RequestBody RegisterParam param,
            @Parameter(hidden = true)
            Errors errors) {
        RespBean result;
        if (errors.hasErrors()) {
            result = new RespBean(-1, "参数错误", errors.getFieldErrors());
        } else {
            result = service.register(param.getUsername(), param.getPassword(), param.getEmail());
        }
        log.info("register: " + result);
        return result;
    }

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
            @ApiResponse(responseCode = "604", description = "用户名或密码错误",
                    content = @Content()),
            @ApiResponse(responseCode = "605", description = "参数错误",
                    content = @Content(schema = @Schema(implementation = Errors.class))),
    })
    public RespBean login(
            @Parameter(required = true, description = "登录参数")
            @RequestBody
            @Valid LoginParam loginParam,
            @Parameter(hidden = true) Errors errors) {
        RespBean result;
        if (errors.hasFieldErrors("username")) {
            result = new RespBean(-1, "参数错误", errors.getFieldErrors());
        } else {
            result = service.login(loginParam.getUsername(), loginParam.getPassword());
        }
        log.info("login: " + result);
        return result;
    }

    @PostMapping("/logout")
    @Operation(summary = "注销登录")
    @ApiResponse(responseCode = "600", description = "注销成功", content = @Content())
    public RespBean logout() {
        RespBean result = service.logout();
        log.info("logout: " + result);
        return result;
    }

    @GetMapping
    @Schema(name = "test")
    public String test() {
        return "hello-world!";
    }

}
