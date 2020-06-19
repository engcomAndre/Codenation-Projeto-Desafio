package com.desafio.codenation.services;

import com.desafio.codenation.domain.origem.Sistema;
import com.desafio.codenation.repositories.SistemaRepositorie;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class SistemaService {

    @Autowired
    private SistemaRepositorie sistemaRepositorie;

    public Sistema getSistema(Long id) {
        return sistemaRepositorie.findById(id).orElse(null);
    }

    public Page<Sistema> getSistemas(Predicate predicate, Pageable pageable) {
        return sistemaRepositorie.findAll(predicate,pageable);
    }

    public Sistema insert(Sistema sistema) {
        if(sistema.getChave() == null || sistema.getChave().isEmpty() ) {
            sistema.setChave(UUID.randomUUID().toString().replace("-", ""));
        }
        return sistemaRepositorie.save(sistema);
    }

    public void deleteSistema(Long id) {
        getSistema(id);
        sistemaRepositorie.deleteById(id);
    }

    public Sistema updateSistema(Long id, Sistema newSistema) {
        Sistema sistema = getSistema(id);

        updtSistema(sistema, newSistema);

        return sistemaRepositorie.save(sistema);

    }

    private void updtSistema(Sistema sistema, Sistema newSistema) {
        if (sistema.getNome() != newSistema.getNome()) {
            sistema.setNome(newSistema.getNome());
        }
        if (sistema.getDescricao() != newSistema.getDescricao()) {
            sistema.setDescricao(newSistema.getDescricao());
        }
        if (sistema.getChave() != newSistema.getChave()) {
            sistema.setChave(newSistema.getChave());
        }

    }

}
