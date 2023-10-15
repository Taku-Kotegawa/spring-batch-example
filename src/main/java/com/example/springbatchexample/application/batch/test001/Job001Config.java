package com.example.springbatchexample.application.batch.test001;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class Job001Config {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager txManager;
    private final TestTasklet testTasklet;

    @Bean
    Job job() {

        Step step1 = new StepBuilder("step1", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("sample job step");
                    return RepeatStatus.FINISHED;
                }, txManager)
                .build();

        Step step2 = new StepBuilder("step2", jobRepository)
                .tasklet(testTasklet, txManager)
                .build();

        return new JobBuilder("job001", jobRepository)
                .incrementer(new RunIdIncrementer()) // 同じパラメーアの再実行を許可する
                .start(step1)
                .next(step2)
                .build();
    }

}
