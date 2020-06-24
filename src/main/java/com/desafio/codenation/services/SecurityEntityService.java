package com.desafio.codenation.services;

import com.desafio.codenation.domain.origem.Origem;
import com.desafio.codenation.domain.security.SecurityEntity;
import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.domain.user.enums.TypeUser;
import com.desafio.codenation.repositories.OrigemRepositorie;
import com.desafio.codenation.repositories.UserRepositorie;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SecurityEntityService implements UserDetailsService {

    private final UserRepositorie userRepositorie;

    private final OrigemRepositorie origemRepositorie;


    public SecurityEntityService(UserRepositorie userRepositorie, OrigemRepositorie origemRepositorie) {
        this.userRepositorie = userRepositorie;
        this.origemRepositorie = origemRepositorie;
    }


    public static Collection<? extends GrantedAuthority> authenticatedGrants() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

    public static Boolean hasGrant(TypeUser typeUser) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority(typeUser.getDesc()));
    }

    public static String authenticatedUsername() {
        try {
            return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepositorie.findByEmail(username).orElse(null);
        if (user != null) {
            return SecurityEntity.builder()
                    .id(user.getId())
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .perfis(user.getPerfis())
                    .build();

        }
        Origem origem = origemRepositorie.findByChaveAndAtivo(username, true)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encotrado para o parâmetros informados."));

        return SecurityEntity.builder()
                .id(origem.getId())
                .username(origem.getChave())
                .password(origem.getPassword())
                .perfis(origem.getPerfis())
                .build();

    }


}