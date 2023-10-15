package com.example.springbatchexample.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class BatchConfiguration {

    private final Environment environment;

    @BatchDataSource
    @Bean(name = "batchDataSource", destroyMethod = "close")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(environment.getProperty("spring.batch.datasource.url"))
                .driverClassName(environment.getProperty("spring.batch.datasource.driverClassName"))
                .username(environment.getProperty("spring.batch.datasource.username"))
                .password(environment.getProperty("spring.batch.datasource.password"))
                .build();
    }

}
