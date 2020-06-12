package com.desafio.codenation.services;

import com.desafio.codenation.domain.origem.Origem;
import com.desafio.codenation.repositories.OriginRepositorie;
import com.desafio.codenation.repositories.UserRepositorie;
import com.desafio.codenation.security.OriginSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private OriginRepositorie originRepositorie;

    @Autowired
    private UserRepositorie userRepositorie;

    //todo trabalhar para busar usuário pelo repositorio;
    @Override
    public UserDetails loadUserByUsername(String identificador) throws UsernameNotFoundException {
        Origem origem = originRepositorie.findByIdentificador(identificador).orElseThrow(() -> new UsernameNotFoundException("Identificador Inválido."));
        String s = ";";
        return new OriginSS(origem.getId(), origem.getIdentificador(),origem.getChave());
    }
}