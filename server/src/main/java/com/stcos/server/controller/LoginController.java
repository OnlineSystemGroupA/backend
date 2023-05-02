package com.stcos.server.controller;

import com.stcos.server.controller.api.LoginApi;
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
public class LoginController implements LoginApi {

    private UserAuthenticationService service;

//////////////////////////////////// AUTO_WIRED BEGIN ///////////////////////////////////////
    @Autowired
    public void setPosService(UserAuthenticationService service) {
        this.service = service;
    }
//////////////////////////////////// AUTO_WIRED END   ///////////////////////////////////////


    @Override
    public RespBean register(RegisterParam param, Errors errors) {
        RespBean result;
        if (errors.hasErrors()) {
            result = new RespBean(-1, "参数错误", errors.getFieldErrors());
        } else {
            result = service.register(param.getUsername(), param.getPassword(), param.getEmail());
        }
        return result;
    }

    @Override
    public RespBean login(LoginParam loginParam, Errors errors) {
        RespBean result;
        if (errors.hasFieldErrors("username")) {
            result = new RespBean(604, "参数错误", errors.getFieldErrors());
        } else {
            result = service.login(loginParam.getUsername(), loginParam.getPassword());
        }
        return result;
    }

    @Override
    public RespBean logout() {
        RespBean result = service.logout();
        return result;
    }


}
