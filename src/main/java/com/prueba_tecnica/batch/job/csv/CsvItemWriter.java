package com.prueba_tecnica.batch.job.csv;

import com.prueba_tecnica.batch.model.Usuario;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class CsvItemWriter {

    @Bean
    public JdbcBatchItemWriter<Usuario> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Usuario>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO usuario (id, nombre, email) VALUES (:id, :nombre, :email)")
                .dataSource(dataSource)
                .build();
    }

}
