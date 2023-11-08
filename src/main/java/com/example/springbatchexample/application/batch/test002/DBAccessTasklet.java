package com.example.springbatchexample.application.batch.test002;

import com.example.springbatchexample.application.mapper.EmployeeMapper;
import com.example.springbatchexample.domain.model.Employee;
import com.example.springbatchexample.domain.model.EmployeeExample;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.builder.MyBatisCursorItemReaderBuilder;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@StepScope
@Component
@RequiredArgsConstructor
public class DBAccessTasklet implements Tasklet {

    private final SqlSessionFactory sqlSessionFactory;
    private final EmployeeMapper employeeMapper;


    @Override
    public RepeatStatus execute(@NonNull StepContribution contribution, @NonNull ChunkContext chunkContext) throws Exception {

        ExecutionContext executionContext = chunkContext.getStepContext().getStepExecution().getExecutionContext();
        ItemStreamReader<Employee> reader = makeReader();
        reader.open(executionContext);

        Employee row;
        while ((row = reader.read()) != null) {
            System.out.println(row);
        }

        return RepeatStatus.FINISHED;
    }

    ItemStreamReader<Employee> makeReader() {

        EmployeeExample example = new EmployeeExample();
        // 絞り込み条件を指定

        Map<String, Object> map = new HashMap<>() {{
                put("oredCriteria", example.getOredCriteria());
                put("orderByClause", example.getOrderByClause());
            }};

        // https://mybatis.org/spring/ja/batch.html
        return new MyBatisCursorItemReaderBuilder<Employee>()
                .queryId("com.example.springbatchexample.application.mapper.EmployeeMapper.selectByExample")
                .sqlSessionFactory(sqlSessionFactory)
                .parameterValues(map)
                .build();
    }


}
