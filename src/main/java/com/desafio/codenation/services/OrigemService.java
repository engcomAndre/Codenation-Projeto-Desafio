package com.desafio.codenation.services;


import com.desafio.codenation.domain.origem.Origem;
import com.desafio.codenation.repositories.OrigemRepositorie;
import com.desafio.codenation.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrigemService {

    @Autowired
    private OrigemRepositorie origemRepositorie;


    public Origem findByIdAndAndChaveAndAtivo(Long id, String chave) {
        return origemRepositorie.findByIdAndChaveAndAtivo(id, chave, true).orElse(null);
    }

    public Origem findById(Long id) {
        return origemRepositorie.findById(id).orElseThrow(() -> new ObjectNotFoundException("Origem não encontrada para os parametros informados."));
    }
}
