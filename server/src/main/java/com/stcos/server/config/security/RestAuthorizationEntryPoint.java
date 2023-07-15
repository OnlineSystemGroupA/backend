package com.stcos.server.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

/*
    ____            __  ___         __  __               _             __  _             ______      __             ____        _       __
   / __ \___  _____/ /_/   | __  __/ /_/ /_  ____  _____(_)___  ____ _/ /_(_)___  ____  / ____/___  / /________  __/ __ \____  (_)___  / /_
  / /_/ / _ \/ ___/ __/ /| |/ / / / __/ __ \/ __ \/ ___/ /_  / / __ `/ __/ / __ \/ __ \/ __/ / __ \/ __/ ___/ / / / /_/ / __ \/ / __ \/ __/
 / _, _/  __(__  ) /_/ ___ / /_/ / /_/ / / / /_/ / /  / / / /_/ /_/ / /_/ / /_/ / / / / /___/ / / / /_/ /  / /_/ / ____/ /_/ / / / / / /_
/_/ |_|\___/____/\__/_/  |_\__,_/\__/_/ /_/\____/_/  /_/ /___/\__,_/\__/_/\____/_/ /_/_____/_/ /_/\__/_/   \__, /_/    \____/_/_/ /_/\__/
                                                                                                          /____/
 */

/**
 * 当未登录或 token失效时访问接口时，自定义的返回结果
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/3/28 21:14
 */
@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(401);
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString("尚未登录，请登录"));
        out.flush();
        out.close();
    }
}
