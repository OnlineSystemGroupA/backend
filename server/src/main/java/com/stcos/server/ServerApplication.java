package com.stcos.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Hello world!
 *
 */
@SpringBootApplication
//scan for "Mapper" package
@MapperScan("com.stcos.server.mapper")
public class ServerApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ServerApplication.class, args);
    }
}
