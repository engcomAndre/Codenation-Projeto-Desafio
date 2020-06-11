package com.desafio.codenation.services;

import com.desafio.codenation.domain.origem.Servico;
import com.desafio.codenation.repositories.ServicoRepositorie;
import com.desafio.codenation.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepositorie servicoRepositorie;

    public Servico getServicoById(Long id) {
        return servicoRepositorie.findById(id).orElseThrow(() -> new ObjectNotFoundException("Serviço com identificador " + id + " não encontrado."));
    }

    public List<Servico> getServicos(Pageable pageable) {
        return servicoRepositorie.findAll(pageable);
    }

    public Servico insert(Servico servico) {
        return servicoRepositorie.save(servico);
    }
}
