package com.desafio.codenation.services;

import com.desafio.codenation.domain.origem.Sistema;
import com.desafio.codenation.repositories.SistemaRepositorie;
import com.desafio.codenation.services.exception.DataIntegrityException;
import com.desafio.codenation.services.exception.ObjectNotFoundException;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;


@Service
public class SistemaService {

    @Autowired
    private SistemaRepositorie sistemaRepositorie;

    public Sistema getSistema(Long id) {
        return sistemaRepositorie.findById(id).orElseThrow(() -> new ObjectNotFoundException("Serviço com identificador " + id + " não encontrado."));
    }

    public Page<Sistema> getSistemas(Predicate predicate, Pageable pageable) {
        Page<Sistema> sistemaPage = sistemaRepositorie.findAll(predicate, pageable);
        if (sistemaPage.isEmpty()) {
            throw new ObjectNotFoundException("Sistema(s) não encontrado(s) para os parâmetros informados.");
        }
        return sistemaRepositorie.findAll(predicate, pageable);
    }

    public Sistema insert(Sistema sistema) {
        if (sistema.getChave() == null || sistema.getChave().isEmpty()) {
            sistema.setChave(UUID.randomUUID().toString().replace("-", ""));
        }
        return sistemaRepositorie.save(sistema);
    }

    public void deleteSistema(Long id) {
        getSistema(id);
        try {
            sistemaRepositorie.deleteById(id);
        } catch (DataIntegrityException die) {
            throw new DataIntegrityException("Não é possível excluir um Sistema que possui registros de evento atrelados.");
        }
    }

    public void updateSistema(Long id, Sistema newSistema) {
        Sistema sistema = getSistema(id);

        updtSistema(sistema, newSistema);

        sistemaRepositorie.save(sistema);

    }

    private void updtSistema(Sistema sistema, Sistema newSistema) {
        if (!Objects.equals(sistema.getNome(), newSistema.getNome())) {
            sistema.setNome(newSistema.getNome());
        }
        if (!Objects.equals(sistema.getDescricao(), newSistema.getDescricao())) {
            sistema.setDescricao(newSistema.getDescricao());
        }
        if (!Objects.equals(sistema.getChave(), newSistema.getChave())) {
            sistema.setChave(newSistema.getChave());
        }

    }

}
