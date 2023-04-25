package com.stcos.server.service.impl;

import com.stcos.server.database.UserRepo;
import com.stcos.server.pojo.RespBean;
import com.stcos.server.pojo.TokenMap;
import com.stcos.server.pojo.entity.User;
import com.stcos.server.service.UserAuthenticationService;
import com.stcos.server.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/4/3 21:01
 */

@Component
public class UserAuthenticationServiceImp implements UserAuthenticationService {

    private UserRepo userRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public RespBean register(String username, String password, String email) {
        User user = new User(username, passwordEncoder.encode(password), email);

        if(userRepo.existUsername(user.getUsername())) //存在对应用户名
            return new RespBean().setCode(601);

        //TODO:校验格式

        userRepo.addNewUser(user);

        return new RespBean().setCode(600);
    }

    private PasswordEncoder passwordEncoder;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RespBean login(String username, String password) {

        UserDetails userDetails = userRepo.getUserByName(username);

        if(userDetails == null) //用户名不存在
            return new RespBean().setCode(601);

        else if (!userDetails.isEnabled()) //用户被禁用
            return new RespBean().setCode(602);

        else if (!passwordEncoder.matches(password, userDetails.getPassword()))
            return new RespBean().setCode(603);

        // 可以用 security 提供的对象进行用户登录
        // 将登录成功地对象方向 security 的全文中
        // 更新 security 用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 生成 token
        String token = JwtTokenUtil.generateToken(userDetails);
        TokenMap tokenMap = new TokenMap(tokenHead, token);

        //TODO:校验格式

        return new RespBean().setCode(600).setObj(tokenMap);
    }

    @Override
    public RespBean logout() {
        return new RespBean().setCode(600);
    }
}
