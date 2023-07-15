package com.stcos.server.config.security;

import com.stcos.server.service.AccountService;
import com.stcos.server.exception.ServiceException;
import com.stcos.server.util.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
       __         __  ___         __  __               __  _            __  _           ______      __              _______ ____
      / /      __/ /_/   | __  __/ /_/ /_  ___  ____  / /_(_)________ _/ /_(_)___  ____/_  __/___  / /_____  ____  / ____(_) / /____  _____
 __  / / | /| / / __/ /| |/ / / / __/ __ \/ _ \/ __ \/ __/ / ___/ __ `/ __/ / __ \/ __ \/ / / __ \/ //_/ _ \/ __ \/ /_  / / / __/ _ \/ ___/
/ /_/ /| |/ |/ / /_/ ___ / /_/ / /_/ / / /  __/ / / / /_/ / /__/ /_/ / /_/ / /_/ / / / / / / /_/ / ,< /  __/ / / / __/ / / / /_/  __/ /
\____/ |__/|__/\__/_/  |_\__,_/\__/_/ /_/\___/_/ /_/\__/_/\___/\__,_/\__/_/\____/_/ /_/_/  \____/_/|_|\___/_/ /_/_/   /_/_/\__/\___/_/

 */

/**
 * JWT 登录授权过滤器
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/3/28 20:55
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private AccountService accountService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader(tokenHeader);
        // 存在 token
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            String authToken = authHeader.substring(tokenHead.length());
            String username = JwtTokenUtil.getUserNameFromToken(authToken);
            String usertype = JwtTokenUtil.getUserTypeFromToken(authToken);
            // token 有效，但是用户未登录
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails;
                // 查找用户
                try {
                    switch (usertype) {
                        case "client" -> userDetails = accountService.getClient(username);
                        case "operator" -> userDetails = accountService.getOperator(username);
                        case "admin" -> userDetails = accountService.getAdmin(username);
                        default -> {
                            filterChain.doFilter(request, response);
                            return;
                        }
                    }
                } catch (ServiceException e) {
                    filterChain.doFilter(request, response);
                    return;
                }
                // 验证 token 是否有效，重新设置用户对象
                if (JwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken token =
                            new UsernamePasswordAuthenticationToken(userDetails, null,
                                    userDetails.getAuthorities());
                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(token);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
