package com.prueba_tecnica.batch.job.csv;

import com.prueba_tecnica.batch.model.Usuario;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class CsvJobConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Job importUserJob(ItemReader<Usuario> csvReader, ItemProcessor<Usuario, Usuario> processor, ItemWriter<Usuario> writer) {
        Step step = new StepBuilder("csvStep", jobRepository)
                .<Usuario, Usuario>chunk(10, transactionManager)
                .reader(csvReader)
                .processor(processor)
                .writer(writer)
                .build();

        return new JobBuilder("importUserJob", jobRepository)
                .start(step)
                .build();
    }
}
