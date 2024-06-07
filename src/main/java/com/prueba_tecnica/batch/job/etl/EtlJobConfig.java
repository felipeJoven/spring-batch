package com.prueba_tecnica.batch.job.etl;

import com.prueba_tecnica.batch.model.Usuario;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class EtlJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobRepository jobRepository;

    @Bean
    public Job etlJob(ItemReader<Usuario> etlReader, ItemProcessor<Usuario, Usuario> processor, ItemWriter<Usuario> writer) {
        Step step = stepBuilderFactory.get("etlStep")
                .<Usuario, Usuario>chunk(10)
                .reader(etlReader)
                .processor(processor)
                .writer(writer)
                .build();

        return jobBuilderFactory.get("etlJob")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .repository(jobRepository)
                .build();
    }
}
