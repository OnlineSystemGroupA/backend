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

    @Autowired
    private UserDetailsFactory userDetailsFactory;

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
                        case "client" ->
                                userDetails = userDetailsFactory.getUserDetails(accountService.getClient(username));
                        case "operator" ->
                                userDetails = userDetailsFactory.getUserDetails(accountService.getOperator(username));
                        case "admin" ->
                                userDetails = userDetailsFactory.getUserDetails(accountService.getAdmin(username));
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
