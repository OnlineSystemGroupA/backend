package com.stcos.server.config.flowable;

import org.flowable.common.engine.impl.AbstractEngineConfiguration;
import org.flowable.common.engine.impl.EngineConfigurator;
import org.flowable.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/4/24 22:52
 */


@Component
public class DatasourceConfigurator implements EngineConfigurator {
    @Value("${flowable.datasource.url}")
    private String url;

    @Value("${flowable.datasource.type}")
    private Class<? extends DataSource> type;

    @Value("${flowable.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${flowable.datasource.username}")
    private String username;

    @Value("${flowable.datasource.password}")
    private String password;


    @Override
    public void beforeInit(AbstractEngineConfiguration engineConfiguration) {
        DataSource dataSource = DataSourceBuilder.create()
                .type(type)
                .driverClassName(driverClassName)
                .url(url)
                .username(username)
                .password(password).build();

        engineConfiguration.setDataSource(dataSource);
    }

    @Override
    public void configure(AbstractEngineConfiguration engineConfiguration) {

        DataSource dataSource = DataSourceBuilder.create()
                .type(type)
                .driverClassName(driverClassName)
                .url(url)
                .username(username)
                .password(password).build();

        engineConfiguration.setDataSource(dataSource);
    }

    @Override
    public int getPriority() {
        return 600000;
    }


    @Lazy
    @Autowired
    ProcessEngine engine;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                DataSource dataSource = engine.getProcessEngineConfiguration().getDataSource();
                System.out.println(dataSource);
            }
        };
    }

}
