package com.desafio.codenation.services;

import com.desafio.codenation.domain.origem.Servico;
import com.desafio.codenation.repositories.ServicoRepositorie;
import com.desafio.codenation.services.exception.ObjectNotFoundException;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepositorie servicoRepositorie;

    public Servico getServicoById(Long id) {
        return servicoRepositorie.findById(id).orElseThrow(() -> new ObjectNotFoundException("Serviço com identificador " + id + " não encontrado."));
    }

    public Page<Servico> getServicos(Predicate predicate, Pageable pageable) {
        return servicoRepositorie.findAll(predicate, pageable);
    }

    public Servico insert(Servico servico) {
        if(servico.getChave() == null || servico.getChave().isEmpty() ) {
            servico.setChave(UUID.randomUUID().toString().replace("-", ""));
        }
        return servicoRepositorie.save(servico);
    }

    public void deleteUser(Long id) {
        getServicoById(id);
        servicoRepositorie.deleteById(id);
    }

    public void updateServico(Long id, Servico newServico) {
        Servico service = getServicoById(id);

        updtServico(service, newServico);

        servicoRepositorie.save(service);

    }

    private void updtServico(Servico servico, Servico newServico) {
        if (!Objects.equals(servico.getNome(), newServico.getNome())) {
            servico.setNome(newServico.getNome());
        }
        if (!Objects.equals(servico.getDescricao(), newServico.getDescricao())) {
            servico.setDescricao(newServico.getDescricao());
        }
        if (!Objects.equals(servico.getChave(), newServico.getChave())) {
            servico.setChave(newServico.getChave());
        }
    }
}
