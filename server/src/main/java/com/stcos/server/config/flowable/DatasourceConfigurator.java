package com.stcos.server.config.flowable;

import org.flowable.common.engine.impl.AbstractEngineConfiguration;
import org.flowable.common.engine.impl.EngineConfigurator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/*
    ____        __                                        ______            _____                        __
   / __ \____ _/ /_____ __________  __  _______________  / ____/___  ____  / __(_)___ ___  ___________ _/ /_____  _____
  / / / / __ `/ __/ __ `/ ___/ __ \/ / / / ___/ ___/ _ \/ /   / __ \/ __ \/ /_/ / __ `/ / / / ___/ __ `/ __/ __ \/ ___/
 / /_/ / /_/ / /_/ /_/ (__  ) /_/ / /_/ / /  / /__/  __/ /___/ /_/ / / / / __/ / /_/ / /_/ / /  / /_/ / /_/ /_/ / /
/_____/\__,_/\__/\__,_/____/\____/\__,_/_/   \___/\___/\____/\____/_/ /_/_/ /_/\__, /\__,_/_/   \__,_/\__/\____/_/
                                                                              /____/
 */

/**
 * 数据源配置类
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
        return 1;
    }

}
