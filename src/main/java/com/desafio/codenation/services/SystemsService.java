package com.desafio.codenation.services;

import com.desafio.codenation.domain.origin.Systems;
import com.desafio.codenation.domain.user.enums.TypeUser;
import com.desafio.codenation.repositories.SystemsRepositorie;
import com.desafio.codenation.services.exception.DataIntegrityException;
import com.desafio.codenation.services.exception.ObjectNotFoundException;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;


@Service
public class SystemsService {

    @Autowired
    private SystemsRepositorie systemsRepositorie;

    public Systems getSistema(Long id) {
        return systemsRepositorie.findById(id).orElseThrow(() -> new ObjectNotFoundException("Serviço com identificador " + id + " não encontrado."));
    }

    public Page<Systems> getSistemas(Predicate predicate, Pageable pageable) {
        Page<Systems> sistemaPage = systemsRepositorie.findAll(predicate, pageable);
        if (sistemaPage.isEmpty()) {
            throw new ObjectNotFoundException("Sistema(s) não encontrado(s) para os parâmetros informados.");
        }
        return systemsRepositorie.findAll(predicate, pageable);
    }

    public Systems insert(Systems systems) {
        systems.setPerfis(new HashSet(Collections.singleton(TypeUser.UNDEFINED)));
        if (systems.getChave() == null || systems.getChave().isEmpty()) {
            systems.setChave(UUID.randomUUID().toString().replace("-", ""));
        }
        return systemsRepositorie.save(systems);
    }

    public void deleteSistema(Long id) {
        getSistema(id);
        try {
            systemsRepositorie.deleteById(id);
        } catch (DataIntegrityException die) {
            throw new DataIntegrityException("Não é possível excluir um Sistema que possui registros de evento atrelados.");
        }
    }

    public void updateSistema(Long id, Systems newSystems) {

        Systems systems = getSistema(id);

        updtSistema(systems, newSystems);

        systemsRepositorie.save(systems);
    }

    private void updtSistema(Systems systems, Systems newSystems) {
        if (!Objects.equals(systems.getNome(), newSystems.getNome())) {
            systems.setNome(newSystems.getNome());
        }
        if (!Objects.equals(systems.getDescricao(), newSystems.getDescricao())) {
            systems.setDescricao(newSystems.getDescricao());
        }
        if (!Objects.equals(systems.getChave(), newSystems.getChave())) {
            systems.setChave(newSystems.getChave());
        }

    }

}
