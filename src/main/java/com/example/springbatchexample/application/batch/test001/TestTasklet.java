package com.example.springbatchexample.application.batch.test001;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;

@StepScope
@Component
public class TestTasklet implements Tasklet {

    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;

    @Autowired
    @Qualifier("batchDataSource")
    DataSource batchDataSource;

    @Autowired
    TransactionManager transactionManager;

//    @Autowired
//    @Qualifier("batchTransactionManager")
//    TransactionManager batchTransactionManager;

    @Autowired
    DefaultListableBeanFactory beanFactory;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    JobExplorer jobExplorer;

    @Autowired
    JobRegistry jobRegistry;

    @Autowired
    JobOperator jobOperator;


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("TestTasklet");

        var list = beanFactory.getBeanDefinitionNames();
        Arrays.stream(list).toList().forEach(System.out::println);

        return RepeatStatus.FINISHED;
    }
}
