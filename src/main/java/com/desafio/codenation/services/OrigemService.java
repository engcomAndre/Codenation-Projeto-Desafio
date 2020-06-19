package com.desafio.codenation.services;


import com.desafio.codenation.domain.origem.Origem;
import com.desafio.codenation.repositories.OrigemRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrigemService {

    @Autowired
    private OrigemRepositorie origemRepositorie;

    public Origem findById(Long id){
        return origemRepositorie.findById(id).orElse(null);
    }

}
