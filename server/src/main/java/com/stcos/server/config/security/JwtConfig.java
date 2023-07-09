package com.stcos.server.config.security;

import com.stcos.server.util.JwtTokenUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/*
               __         __  ______            _____
              / /      __/ /_/ ____/___  ____  / __(_)___ _
         __  / / | /| / / __/ /   / __ \/ __ \/ /_/ / __ `/
        / /_/ /| |/ |/ / /_/ /___/ /_/ / / / / __/ / /_/ /
        \____/ |__/|__/\__/\____/\____/_/ /_/_/ /_/\__, /
                                                  /____/
 */

/**
 * JWT 配置类
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
    public void afterPropertiesSet() {
        JwtTokenUtil.setSecret(secret);
        JwtTokenUtil.setExpiration(expiration);
    }
}
