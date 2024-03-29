package com.stcos.server.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/*
           _____                      _ __        ______            _____
          / ___/___  _______  _______(_) /___  __/ ____/___  ____  / __(_)___ _
          \__ \/ _ \/ ___/ / / / ___/ / __/ / / / /   / __ \/ __ \/ /_/ / __ `/
         ___/ /  __/ /__/ /_/ / /  / / /_/ /_/ / /___/ /_/ / / / / __/ / /_/ /
        /____/\___/\___/\__,_/_/  /_/\__/\__, /\____/\____/_/ /_/_/ /_/\__, /
                                        /____/                        /____/
 */

/**
 * spring-security 配置类
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/3/30 17:11
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   RestAuthorizationEntryPoint restAuthorizationEntryPoint,
                                                   RestfulAccessDeniedHandler restfulAccessDeniedHandler,
                                                   JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter) throws Exception {
        http.headers().frameOptions().sameOrigin(); // 允许来自同一来源的 H2 控制台的请求
        http.authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll();


        http.csrf().disable()
                // 基于 token，不需要 session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 允许登录访问
                .authorizeHttpRequests().requestMatchers(
                        "/hello",
                        "/auth/login",
                        "/auth/logout",
                        "/auth/register",
                        "/css/**",
                        "/js/**",
                        "/index.html",
                        "favicon.ico",
                        "/doc.html",
                        "/webjars/**",
                        "/swagger-resources/**",
                        "/v3/api-docs/**"
                ).permitAll()
                // 除了上面，所有请求都要拦截
                .anyRequest().authenticated()
                .and()
                .logout().disable()
//                .and()
//                .formLogin()
//                .loginPage("/")
//                .defaultSuccessUrl("/home", true)
                // 禁用缓存
                .headers().cacheControl();
        // 添加 jwt 登录授权过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);


        // 添加自定义未授权和未登录结果返回
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthorizationEntryPoint);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

/*                       ,%%%%%%%%,
 *                      ,%%/\%%%%/\%%
 *                     ,%%%\c''''J/%%%
 *           %.        %%%%/ o  o \%%%
 *           `%%.      %%%%       |%%%
 *            `%%      `%%%%(__Y__)%%'
 *            //        ;%%%%`\-/%%%'
 *            ((      /   `%%%%%%%'
 *             \\     .'           |
 *              \\   /        \  | |
 *               \\/          ) | |
 *                \          /_ | |__
 *                (____________))))))) 攻城狮
 *
 */