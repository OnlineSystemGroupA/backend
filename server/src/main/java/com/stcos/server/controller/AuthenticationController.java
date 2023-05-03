package com.stcos.server.controller;


import com.stcos.server.controller.api.AuthenticationApi;
import com.stcos.server.pojo.dto.LoginParamDto;
import com.stcos.server.pojo.dto.RegisterParamDto;
import com.stcos.server.pojo.dto.TokenDto;
import com.stcos.server.service.AuthenticationService;
import com.stcos.server.service.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class AuthenticationController implements AuthenticationApi {

    private AuthenticationService service;


    @Autowired
    public void setPosService(AuthenticationService service) {
        this.service = service;
    }


    @Override
    public ResponseEntity<TokenDto> login(String usertype, LoginParamDto loginParamDto) {
        return AuthenticationApi.super.login(usertype, loginParamDto);
    }

    @Override
    public ResponseEntity<Void> register(RegisterParamDto registerParamDto) {
        try {
            service.register(registerParamDto.getUsername(), registerParamDto.getPassword(), registerParamDto.getEmail());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> logout() {
        return AuthenticationApi.super.logout();
    }
}
