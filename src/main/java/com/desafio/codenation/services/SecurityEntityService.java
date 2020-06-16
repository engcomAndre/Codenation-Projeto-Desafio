package com.desafio.codenation.services;

import com.desafio.codenation.domain.security.SecurityEntity;
import com.desafio.codenation.repositories.OriginRepositorie;
import com.desafio.codenation.repositories.SecurityRepositorie;
import com.desafio.codenation.repositories.UserRepositorie;
import com.desafio.codenation.security.OriginSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityEntityService implements UserDetailsService {

    @Autowired
    private SecurityRepositorie securityRepositorie;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return securityRepositorie.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username ou Identificador inválido"));
    }

    public static SecurityEntity authenticated(){
        try{
            return (SecurityEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception e){
            return null;
        }
    }
}