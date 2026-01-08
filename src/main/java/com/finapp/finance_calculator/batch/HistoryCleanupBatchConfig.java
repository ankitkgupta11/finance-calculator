package com.finapp.finance_calculator.batch;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.finapp.finance_calculator.vo.CalculationHistoryVO;

import java.time.LocalDateTime;
import java.util.Map;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class HistoryCleanupBatchConfig {

    @Value("${history.retention-days}")
    private int retentionDays;

    // ---------- READER ----------
    @Bean
    @StepScope
    public JpaPagingItemReader<CalculationHistoryVO> cleanupReader(
            EntityManagerFactory emf) {

        JpaPagingItemReader<CalculationHistoryVO> reader =
                new JpaPagingItemReader<>();

        reader.setEntityManagerFactory(emf);
        reader.setParameterValues(
                Map.of("cutoff", LocalDateTime.now().minusDays(retentionDays))
            );
        reader.setQueryString(
            "SELECT c FROM CalculationHistoryVO c WHERE c.createdAt < :cutoff"
        );
        
        reader.setPageSize(100);
        return reader;
    }

    // ---------- WRITER (DELETE) ----------
    @Bean
    @StepScope
    public JdbcBatchItemWriter<CalculationHistoryVO> cleanupWriter(DataSource dataSource) {

        JdbcBatchItemWriter<CalculationHistoryVO> writer =
                new JdbcBatchItemWriter<>();

        writer.setDataSource(dataSource);
        writer.setSql("DELETE FROM calculation_history WHERE id = :id");
        writer.setItemSqlParameterSourceProvider(
                new BeanPropertyItemSqlParameterSourceProvider<>()
        );

        return writer;
    }


    // ---------- STEP ----------
    @Bean
    public Step cleanupStep(JobRepository jobRepository,
                            PlatformTransactionManager txManager,
                            EntityManagerFactory emf, DataSource dataSource) {

        return new StepBuilder("cleanupStep", jobRepository)
                .<CalculationHistoryVO, CalculationHistoryVO>chunk(100, txManager)
                .reader(cleanupReader(emf))
                .writer(cleanupWriter(dataSource))
                .build();
    }

    // ---------- JOB ----------
    @Bean
    public Job cleanupHistoryJob(JobRepository jobRepository, Step cleanupStep) {
        return new JobBuilder("cleanupHistoryJob", jobRepository)
                .start(cleanupStep)
                .build();
    }
}
