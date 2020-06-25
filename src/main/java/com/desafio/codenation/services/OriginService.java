package com.desafio.codenation.services;


import com.desafio.codenation.domain.origin.Origins;
import com.desafio.codenation.repositories.OriginsRepositorie;
import com.desafio.codenation.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OriginService {

    @Autowired
    private OriginsRepositorie originsRepositorie;


    public Origins findByIdAndAndChaveAndAtivo(Long id, String chave) {
        return originsRepositorie.findByIdAndChaveAndAtivo(id, chave, true).orElse(null);
    }

    public Origins findById(Long id) {
        return originsRepositorie.findById(id).orElseThrow(() -> new ObjectNotFoundException("Origem não encontrada para os parametros informados."));
    }
}
