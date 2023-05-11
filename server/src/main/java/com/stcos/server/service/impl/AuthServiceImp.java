package com.stcos.server.service.impl;

import com.stcos.server.pojo.dto.TokenDto;
import com.stcos.server.service.AuthService;
import com.stcos.server.service.ServiceException;
import com.stcos.server.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * description
 *
 * @author Mikeal-C
 * @version 1.0
 * @since 2023/4/3 21:01
 */

@Service
public class AuthServiceImp implements AuthService {

    private UserDetailsService userDetailsService;

    @Autowired
     public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public TokenDto login(String username, String password) throws ServiceException {
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e){ //用户不存在
            throw new ServiceException(0);
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())) //用户名与密码不匹配
            throw new ServiceException(1);

        else if (!userDetails.isEnabled()) //用户被禁用
            throw new ServiceException(2);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 生成 token
        String token = JwtTokenUtil.generateToken(userDetails);

        return new TokenDto(tokenHead, token);
    }

    // 目前不需要实现
    @Override
    public void logout() throws ServiceException {
    }

}
