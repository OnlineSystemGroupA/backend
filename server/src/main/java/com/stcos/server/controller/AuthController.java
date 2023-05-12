package com.stcos.server.controller;

import com.stcos.server.controller.api.AuthApi;
import com.stcos.server.pojo.dto.LoginParamDto;
import com.stcos.server.pojo.dto.RegisterParamDto;
import com.stcos.server.pojo.dto.TokenDto;
import com.stcos.server.service.AccountService;
import com.stcos.server.service.AuthService;
import com.stcos.server.service.ServiceException;
import com.stcos.server.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/*
    ___         __  __               __  _            __  _             ______            __             ____
   /   | __  __/ /_/ /_  ___  ____  / /_(_)________ _/ /_(_)___  ____  / ____/___  ____  / /__________  / / /__  _____
  / /| |/ / / / __/ __ \/ _ \/ __ \/ __/ / ___/ __ `/ __/ / __ \/ __ \/ /   / __ \/ __ \/ __/ ___/ __ \/ / / _ \/ ___/
 / ___ / /_/ / /_/ / / /  __/ / / / /_/ / /__/ /_/ / /_/ / /_/ / / / / /___/ /_/ / / / / /_/ /  / /_/ / / /  __/ /
/_/  |_\__,_/\__/_/ /_/\___/_/ /_/\__/_/\___/\__,_/\__/_/\____/_/ /_/\____/\____/_/ /_/\__/_/   \____/_/_/\___/_/

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

    @Autowired
    public void setAuthService(AuthService service) {
        this.authService = service;
    }

    private AccountService accountService;

    @Autowired
    public  void setAccountService(AccountService service){ this.accountService = service; }

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public ResponseEntity<TokenDto> login(LoginParamDto loginParamDto, String userType) {
        ResponseEntity<TokenDto> response = null;
        UserDetails userDetails = null;
        try {
            userDetails = authService.login(loginParamDto.getUsername(), loginParamDto.getPassword(), userType);
        } catch (ServiceException e) {
            switch (e.getCode()) {
                case 0, 3 -> response = ResponseEntity.status(404).build();
                case 1 -> response = ResponseEntity.status(401).build();
                case 2 -> response = ResponseEntity.status(403).build();
                default -> throw new RuntimeException();
            }
        }
        if (response == null) {
            // 生成 token
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            String token = JwtTokenUtil.generateToken(userDetails);
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
                // 用户名已存在
                response = ResponseEntity.status(409).build();
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
