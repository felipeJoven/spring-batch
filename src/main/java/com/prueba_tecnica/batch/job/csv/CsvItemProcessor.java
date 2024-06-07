package com.prueba_tecnica.batch.job.csv;

import com.prueba_tecnica.batch.model.Usuario;
import com.prueba_tecnica.batch.validator.EmailValidator;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CsvItemProcessor implements ItemProcessor<Usuario, Usuario> {

    @Autowired
    private EmailValidator emailValidator;

    @Override
    public Usuario process(Usuario usuario) throws Exception {
        usuario.setNombre(usuario.getNombre().toUpperCase());
        if (!emailValidator.isValid(usuario.getEmail())) {
            throw new IllegalArgumentException("Invalid email: " + usuario.getEmail());
        }
        return usuario;
    }
}
