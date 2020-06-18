package com.desafio.codenation.services;

import com.desafio.codenation.domain.origem.Sistema;
import com.desafio.codenation.repositories.SistemaRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SistemaService {

    @Autowired
    private SistemaRepositorie sistemaRepositorie;

    public Sistema getSistema(Long id) {
        return sistemaRepositorie.findById(id).orElse(null);
    }

    public List<Sistema> getSistemas() {
        return sistemaRepositorie.findAll();
    }

    public Sistema insert(Sistema sistema) {
        return sistemaRepositorie.save(sistema);
    }
}
