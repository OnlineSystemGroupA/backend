package com.stcos.server.config.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

/*
           ____                   ___    ____  __________            _____
          / __ \____  ___  ____  /   |  / __ \/  _/ ____/___  ____  / __(_)___ _
         / / / / __ \/ _ \/ __ \/ /| | / /_/ // // /   / __ \/ __ \/ /_/ / __ `/
        / /_/ / /_/ /  __/ / / / ___ |/ ____// // /___/ /_/ / / / / __/ / /_/ /
        \____/ .___/\___/_/ /_/_/  |_/_/   /___/\____/\____/_/ /_/_/ /_/\__, /
            /_/                                                        /____/
 */

/**
 * 在线接口文档配置类
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/4/4 10:45
 */
@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                        .title("南大软件测试中心在线系统")
                        .description("后端 API 文档")
                        .contact(new Contact()
                                .name("在线系统项目 A 组")
                                .url("https://github.com/OnlineSystemGroupA")
                                .email("support@example.com"))
                        .version("1.0.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .servers(
                        new ArrayList<>() {{
                            add(new Server().url("http://localhost:8081/"));
                        }}
                );
    }
}
