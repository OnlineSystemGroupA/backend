package com.stcos.server.config.flowable;

import org.flowable.app.spring.SpringAppEngineConfiguration;
import org.flowable.cmmn.engine.CmmnEngineConfiguration;
import org.flowable.dmn.engine.DmnEngineConfiguration;
import org.flowable.eventregistry.spring.SpringEventRegistryEngineConfiguration;
import org.flowable.idm.engine.IdmEngineConfiguration;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/4/24 11:54
 */

@Configuration
public class ProcessEngineConfig {

    private DatasourceConfigurator datasourceConfigurator;

    @Autowired
    public void setDatasourceConfigurator(DatasourceConfigurator datasourceConfigurator) {
        this.datasourceConfigurator = datasourceConfigurator;
    }

    @Bean
    public EngineConfigurationConfigurer<SpringProcessEngineConfiguration> SpringProcessEngineConfigurationConfigurer() {
        return engineConfiguration -> engineConfiguration.addConfigurator(datasourceConfigurator);
    }

    @Bean
    public EngineConfigurationConfigurer<IdmEngineConfiguration> IdmEngineConfigurationConfigurer() {
        return engineConfiguration -> engineConfiguration.addConfigurator(datasourceConfigurator);
    }

    @Bean
    public EngineConfigurationConfigurer<CmmnEngineConfiguration> CmmnEngineConfigurationConfigurer() {
        return engineConfiguration -> engineConfiguration.addConfigurator(datasourceConfigurator);
    }

    @Bean
    public EngineConfigurationConfigurer<DmnEngineConfiguration> DmnEngineConfigurationConfigurer() {
        return engineConfiguration -> engineConfiguration.addConfigurator(datasourceConfigurator);
    }

    @Bean
    public EngineConfigurationConfigurer<SpringEventRegistryEngineConfiguration> SpringEventRegistryEngineConfigurationConfigurer() {
        return engineConfiguration -> engineConfiguration.addConfigurator(datasourceConfigurator);
    }

    @Bean
    public EngineConfigurationConfigurer<SpringAppEngineConfiguration> SpringAppEngineConfigurationConfigurer() {
        return engineConfiguration -> engineConfiguration.addConfigurator(datasourceConfigurator);
    }

}

