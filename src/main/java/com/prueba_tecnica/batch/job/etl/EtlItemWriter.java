package com.prueba_tecnica.batch.job.etl;

import com.prueba_tecnica.batch.model.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class EtlItemWriter implements ItemWriter<Usuario> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void write(Chunk<? extends Usuario> chunk) throws Exception {
        for (Usuario usuario : chunk) {
            jdbcTemplate.update("INSERT INTO usuario (id, nombre, email) VALUES (?, ?, ?)",
                    usuario.getId(), usuario.getNombre(), usuario.getEmail());
        }
    }
}
