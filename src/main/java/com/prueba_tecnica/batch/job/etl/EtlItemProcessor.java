package com.prueba_tecnica.batch.job.etl;

import com.prueba_tecnica.batch.model.Usuario;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class EtlItemProcessor implements ItemProcessor<Usuario, Usuario> {

    @Override
    public Usuario process(Usuario usuario) throws Exception {
        usuario.setNombre(usuario.getNombre().toLowerCase());
        return usuario;
    }
}
