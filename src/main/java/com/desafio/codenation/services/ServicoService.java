package com.desafio.codenation.services;

import com.desafio.codenation.domain.origem.Servico;
import com.desafio.codenation.repositories.ServicoRepositorie;
import com.desafio.codenation.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepositorie servicoRepositorie;

    public Servico getServicoById(Long id) {
        return servicoRepositorie.findById(id).orElseThrow(() -> new ObjectNotFoundException("Serviço com identificador " + id + " não encontrado."));
    }

    public Page<Servico> getServicos(Pageable pageable) {
        return servicoRepositorie.findAll(pageable);
    }

    public Servico insert(Servico servico) {
        return servicoRepositorie.save(servico);
    }
}
