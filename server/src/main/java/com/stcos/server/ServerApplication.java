package com.stcos.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class ServerApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ServerApplication.class, args);
    }
}
