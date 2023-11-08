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
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionManager;

@StepScope
@Component
public class TestTasklet implements Tasklet {

    int i = 0;

    @Autowired
    TransactionManager transactionManager;

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

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public RepeatStatus execute(@NonNull StepContribution contribution, @NonNull ChunkContext chunkContext) throws Exception {
        System.out.println("TestTasklet");

//        Thread.sleep(10000);

        System.out.println(i);
        i++;

        return RepeatStatus.FINISHED;
    }
}
