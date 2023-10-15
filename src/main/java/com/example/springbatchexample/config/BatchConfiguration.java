package com.example.springbatchexample.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
public class BatchConfiguration {

    @org.springframework.boot.autoconfigure.batch.BatchDataSource
    @Bean("batchDS")
    public DataSource dataSource(BatchDataSource batchDataSource) {
        return DataSourceBuilder.create()
                .url(batchDataSource.getUrl())
                .driverClassName(batchDataSource.getDriverClassName())
                .username(batchDataSource.getUsername())
                .password(batchDataSource.getPassword())
                .build();
    }

    @Component
    @ConfigurationProperties(prefix = "spring.batch.datasource")
    @Data
    private class BatchDataSource {
        private String url;
        private String driverClassName;
        private String username;
        private String password;
    }

}
