package com.prueba_tecnica.batch.job.csv;

import com.prueba_tecnica.batch.model.Usuario;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class CsvItemReader {

    @Bean
    public FlatFileItemReader<Usuario> csvReader() {
        return new FlatFileItemReaderBuilder<Usuario>()
                .name("csvUserItemReader")
                .resource(new ClassPathResource("data.csv"))
                .delimited()
                .names(new String[]{"id", "nombre", "email"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(Usuario.class);
                }})
                .build();
    }
}
