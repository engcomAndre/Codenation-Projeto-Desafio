package com.desafio.codenation.services;


import com.desafio.codenation.domain.origin.Origins;
import com.desafio.codenation.repositories.OriginsRepositorie;
import org.springframework.stereotype.Service;


@Service
public class OriginService {

    private final OriginsRepositorie originsRepositorie;

    public OriginService(OriginsRepositorie originsRepositorie) {
        this.originsRepositorie = originsRepositorie;
    }

    public Origins findByIdAndAndOriginKeyAndAtivo(Long id, String chave) {
        return originsRepositorie.findByIdAndOriginKeyAndActive(id, chave, true).orElse(null);
    }

//    public Origins findById(Long id) {
//        return originsRepositorie.findById(id).orElseThrow(() -> new ObjectNotFoundException("Origem não encontrada para os parametros informados."));
//    }
}
