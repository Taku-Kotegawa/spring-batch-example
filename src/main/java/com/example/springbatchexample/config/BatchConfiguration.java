package com.example.springbatchexample.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class BatchConfiguration {

    private final Environment environment;

    @SuppressWarnings("unchecked")
    @Primary
    @Bean
    public DataSource dataSource() throws ClassNotFoundException {

        Class<DataSource> clazz;
        String type = environment.getProperty("spring.datasource.type");
        if (type != null) {
            clazz = (Class<DataSource>) Class.forName(type);
        } else {
            clazz = null;
        }

        return DataSourceBuilder.create()
                .url(environment.getProperty("spring.datasource.url"))
                .driverClassName(environment.getProperty("spring.datasource.driverClassName"))
                .username(environment.getProperty("spring.datasource.username"))
                .password(environment.getProperty("spring.datasource.password"))
                .type(clazz)
                .build();
    }

    @SuppressWarnings("unchecked")
    @BatchDataSource
    @Bean
    public DataSource batchDataSource() throws ClassNotFoundException {

        Class<DataSource> clazz;
        String type = environment.getProperty("spring.batch.datasource.type");
        if (type != null) {
            clazz = (Class<DataSource>) Class.forName(type);
        } else {
            clazz = null;
        }

        return DataSourceBuilder.create()
                .url(environment.getProperty("spring.batch.datasource.url"))
                .driverClassName(environment.getProperty("spring.batch.datasource.driverClassName"))
                .username(environment.getProperty("spring.batch.datasource.username"))
                .password(environment.getProperty("spring.batch.datasource.password"))
                .type(clazz)
                .build();
    }

}
