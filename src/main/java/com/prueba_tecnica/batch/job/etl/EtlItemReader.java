package com.prueba_tecnica.batch.job.etl;

import com.prueba_tecnica.batch.model.Usuario;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class EtlItemReader {

    @Bean
    public JdbcCursorItemReader<Usuario> etlReader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Usuario>()
                .dataSource(dataSource)
                .name("etlUserItemReader")
                .sql("SELECT id, nombre, email FROM source_users")
                .rowMapper((rs, rowNum) -> new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email")))
                .build();
    }
}
