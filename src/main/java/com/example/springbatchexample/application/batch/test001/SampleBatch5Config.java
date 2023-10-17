package com.example.springbatchexample.application.batch.test001;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class SampleBatch5Config {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager txManager;
    private final TestTasklet testTasklet;

    @Bean
    Step step1() {
        return new StepBuilder("step1", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("sample job step");
                    return RepeatStatus.FINISHED;
                }, txManager)
                .build();
    }

    @Bean
    Step step2() {
        return new StepBuilder("step2", jobRepository)
                .tasklet(testTasklet, txManager)
                .build();
    }

    @Bean
    Job sampleJob(Step step1, Step step2) {
        return new JobBuilder("sampleJob", jobRepository)
                // .incrementer(new RunIdIncrementer())
                .start(step1)
                .next(step2)
                .build();
    }

}
