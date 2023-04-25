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

/*
        ____                                 ______            _            ______            _____
       / __ \_________  ________  __________/ ____/___  ____ _(_)___  ___  / ____/___  ____  / __(_)___ _
      / /_/ / ___/ __ \/ ___/ _ \/ ___/ ___/ __/ / __ \/ __ `/ / __ \/ _ \/ /   / __ \/ __ \/ /_/ / __ `/
     / ____/ /  / /_/ / /__/  __(__  |__  ) /___/ / / / /_/ / / / / /  __/ /___/ /_/ / / / / __/ / /_/ /
    /_/   /_/   \____/\___/\___/____/____/_____/_/ /_/\__, /_/_/ /_/\___/\____/\____/_/ /_/_/ /_/\__, /
                                                     /____/                                     /____/
 */


/**
 * 自定义流程引擎配置，当前仅用作数据换源
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
    public EngineConfigurationConfigurer<SpringProcessEngineConfiguration> springProcessEngineConfigurationConfigurer() {
        return engineConfiguration -> engineConfiguration.addConfigurator(datasourceConfigurator);
    }

    @Bean
    public EngineConfigurationConfigurer<IdmEngineConfiguration> idmEngineConfigurationConfigurer() {
        return engineConfiguration -> engineConfiguration.addConfigurator(datasourceConfigurator);
    }

    @Bean
    public EngineConfigurationConfigurer<CmmnEngineConfiguration> cmmnEngineConfigurationConfigurer() {
        return engineConfiguration -> engineConfiguration.addConfigurator(datasourceConfigurator);
    }

    @Bean
    public EngineConfigurationConfigurer<DmnEngineConfiguration> dmnEngineConfigurationConfigurer() {
        return engineConfiguration -> engineConfiguration.addConfigurator(datasourceConfigurator);
    }

    @Bean
    public EngineConfigurationConfigurer<SpringEventRegistryEngineConfiguration> springEventRegistryEngineConfigurationConfigurer() {
        return engineConfiguration -> engineConfiguration.addConfigurator(datasourceConfigurator);
    }

    @Bean
    public EngineConfigurationConfigurer<SpringAppEngineConfiguration> springAppEngineConfigurationConfigurer() {
        return engineConfiguration -> engineConfiguration.addConfigurator(datasourceConfigurator);
    }

}

