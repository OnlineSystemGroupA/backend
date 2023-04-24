package com.stcos.server.config.flowable;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.*;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.flowable.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/4/24 11:54
 */

@Configuration
public class ProcessEngineConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.flowable")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Override
    public void configure(SpringProcessEngineConfiguration engineConfiguration) {
        engineConfiguration.setDataSource(dataSource());
    }
}

