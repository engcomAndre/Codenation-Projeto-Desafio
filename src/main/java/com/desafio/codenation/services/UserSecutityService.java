package com.desafio.codenation.services;

import com.desafio.codenation.domain.user.Origin;
import com.desafio.codenation.repositories.OriginRepositorie;
import com.desafio.codenation.security.OriginSS;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecutityService implements UserDetailsService {

    @Autowired
    private OriginRepositorie originRepositorie;

    @Override
    public UserDetails loadUserByUsername(String identificador) throws UsernameNotFoundException {
        Origin origin = originRepositorie.findByIdentificador(identificador).orElseThrow(() -> new UsernameNotFoundException("Identificador Inválido."));
        return new OriginSS(origin.getId(), origin.getIdentificador(),origin.getChave());
    }
}