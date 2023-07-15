package com.stcos.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 服务器应用程序入口点
 */
@SpringBootApplication
//scan for "Mapper" package
@MapperScan("com.stcos.server.database")
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
