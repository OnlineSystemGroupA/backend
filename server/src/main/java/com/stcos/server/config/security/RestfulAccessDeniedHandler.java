package com.stcos.server.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

/*
    ____            __  ____      _____                            ____             _          ____  __                ____
   / __ \___  _____/ /_/ __/_  __/ /   | _____________  __________/ __ \___  ____  (_)__  ____/ / / / /___ _____  ____/ / /__  _____
  / /_/ / _ \/ ___/ __/ /_/ / / / / /| |/ ___/ ___/ _ \/ ___/ ___/ / / / _ \/ __ \/ / _ \/ __  / /_/ / __ `/ __ \/ __  / / _ \/ ___/
 / _, _/  __(__  ) /_/ __/ /_/ / / ___ / /__/ /__/  __(__  |__  ) /_/ /  __/ / / / /  __/ /_/ / __  / /_/ / / / / /_/ / /  __/ /
/_/ |_|\___/____/\__/_/  \__,_/_/_/  |_\___/\___/\___/____/____/_____/\___/_/ /_/_/\___/\__,_/_/ /_/\__,_/_/ /_/\__,_/_/\___/_/

 */

/**
 * 当访问接口没有权限时，自定义返回结果
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/3/28 21:18
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(403);
        response.getWriter();
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString("权限不足，请联系管理员！"));
        out.flush();
        out.close();
    }
}
