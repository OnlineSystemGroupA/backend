package com.stcos.server.controller;

import com.stcos.server.controller.api.AuthApi;
import com.stcos.server.entity.dto.LoginParamDto;
import com.stcos.server.entity.dto.RegisterParamDto;
import com.stcos.server.entity.dto.TokenDto;
import com.stcos.server.service.AccountService;
import com.stcos.server.service.AuthService;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/*
            ___         __  __    ______            __             ____
           /   | __  __/ /_/ /_  / ____/___  ____  / /__________  / / /__  _____
          / /| |/ / / / __/ __ \/ /   / __ \/ __ \/ __/ ___/ __ \/ / / _ \/ ___/
         / ___ / /_/ / /_/ / / / /___/ /_/ / / / / /_/ /  / /_/ / / /  __/ /
        /_/  |_\__,_/\__/_/ /_/\____/\____/_/ /_/\__/_/   \____/_/_/\___/_/
 */

/**
 * 实现用户认证的接口
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/3/29 15:27
 */
@RestController
public class AuthController implements AuthApi {

    private AuthService authService;

    private AccountService accountService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;


    /*   AUTO_WIRED BEGIN  */
    @Autowired
    public void setAuthService(AuthService service) {
        this.authService = service;
    }

    @Autowired
    public void setAccountService(AccountService service) {
        this.accountService = service;
    }
    /*   AUTO_WIRED END    */


    @Override
    public ResponseEntity<TokenDto> login(LoginParamDto loginParamDto, String userType) {
        ResponseEntity<TokenDto> response = null;
        UserDetails userDetails = null;
        try {
            // 调用认证服务
            userDetails = authService.login(loginParamDto.getUsername(), loginParamDto.getPassword(), userType);
        } catch (ServiceException e) {
            switch (e.getCode()) {
                case 0, 3 -> response = ResponseEntity.status(404).build();     // 用户名或用户类型不存在
                case 1 -> response = ResponseEntity.status(401).build();        // 用户名或密码错误
                case 2 -> response = ResponseEntity.status(403).build();        // 账号被禁用
                default -> throw new RuntimeException();
            }
        }
        if (response == null) {
            // 生成 token
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            String token = JwtTokenUtil.generateToken(userDetails, userType);
            response = ResponseEntity.ok(new TokenDto(tokenHead, token));
        }
        return response;
    }


    @Override
    public ResponseEntity<Void> register(RegisterParamDto registerParamDto) {
        ResponseEntity<Void> response = null;
        try {
            accountService.register(registerParamDto.getUsername(), registerParamDto.getPassword(), registerParamDto.getEmail());
        } catch (ServiceException e) {
            if (e.getCode() == 0) {
                response = ResponseEntity.status(409).build(); // 用户名已存在
            }
        }
        if (response == null) {
            response = ResponseEntity.ok().build();
        }
        return response;
    }


    @Override
    public ResponseEntity<Void> logout() {
        try {
            authService.logout();
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(null);
    }
}
