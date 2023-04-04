package com.stcos.server.config.security;

import com.stcos.server.util.JwtTokenUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/3/31 19:59
 */

@Configuration
public class JwtConfig implements InitializingBean {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Override
    public void afterPropertiesSet() throws Exception {
        JwtTokenUtil.setSecret(secret);
        JwtTokenUtil.setExpiration(expiration);
    }
}
