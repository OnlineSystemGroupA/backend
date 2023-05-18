package com.stcos.server;

import com.stcos.server.service.impl.EmailServiceImp;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * Hello world!
 */
@SpringBootApplication
//scan for "Mapper" package
@MapperScan("com.stcos.server.mapper")
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
