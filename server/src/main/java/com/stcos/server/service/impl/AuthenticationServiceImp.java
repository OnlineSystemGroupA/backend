package com.stcos.server.service.impl;

import com.stcos.server.mapper.ClientMapper;
import com.stcos.server.pojo.dto.TokenDto;
import com.stcos.server.pojo.po.Admin;
import com.stcos.server.pojo.po.Client;
import com.stcos.server.pojo.po.Operator;
import com.stcos.server.service.AuthenticationService;
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
public class AuthenticationServiceImp implements AuthenticationService {

    private UserDetailsService userDetailsService;

    @Autowired
     public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    private ClientMapper clientMapper;

//    @Autowired
    public void setClientMapper(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public void register(String username, String password, String email) throws ServiceException {
        try {
            userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) { //不存在用户名
            Client client = new Client(username, passwordEncoder.encode(password), email);
            clientMapper.addNewUser(client);
            return ;
        }

        throw new ServiceException(0);
    }

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

        if (userDetails instanceof Admin) {
            return new TokenDto(tokenHead, token, "admin");
        } else if (userDetails instanceof Operator) {
            return new TokenDto(tokenHead, token, "operator");
        } else if (userDetails instanceof Client) {
            return new TokenDto(tokenHead, token, "client");
        }
        return new TokenDto(tokenHead, token, null);
    }

    // 目前不需要实现
    @Override
    public void logout() throws ServiceException {
    }

}
