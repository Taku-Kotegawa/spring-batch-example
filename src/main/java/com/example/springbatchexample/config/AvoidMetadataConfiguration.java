package com.example.springbatchexample.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
public class AvoidMetadataConfiguration {

    @BatchDataSource
    @Bean("batchDS")
    public DataSource dataSource(BatchDataSorce batchDataSorce) {
        return DataSourceBuilder.create()
                .url(batchDataSorce.getUrl())
                .driverClassName(batchDataSorce.getDriverClassName())
                .username(batchDataSorce.getUsername())
                .password(batchDataSorce.getPassword())
                .build();
    }

    @Component
    @ConfigurationProperties(prefix = "spring.batch.datasource")
    @Data
    private class BatchDataSorce {
        private String url;
        private String driverClassName;
        private String username;
        private String password;
    }

}
