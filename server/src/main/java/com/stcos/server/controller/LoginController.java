package com.stcos.server.controller;

import com.stcos.server.pojo.LoginParam;
import com.stcos.server.pojo.RespObj;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/3/29 15:27
 */

@Slf4j
@RestController
@RequestMapping("/login")
@Tag(name = "LoginController")
public class LoginController {

    @PostMapping
    @Schema(name = "login", description = "登录")
    public RespObj login(LoginParam param, HttpServletRequest request) {
//        return RespObj.success("200");


        return RespObj.error("错误");
    }



}
