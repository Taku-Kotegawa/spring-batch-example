package com.example.springbatchexample.application.batch.test003;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

// @StepScope
@Component
public class NoStepScopeTasklet implements Tasklet {

    int i = 0;

    @Override
    public RepeatStatus execute(@NonNull StepContribution contribution, @NonNull ChunkContext chunkContext) throws Exception {
        System.out.println("NoStepScopeTasklet");

//        Thread.sleep(10000);

        System.out.println(i);
        i++;

        return RepeatStatus.FINISHED;
    }
}
