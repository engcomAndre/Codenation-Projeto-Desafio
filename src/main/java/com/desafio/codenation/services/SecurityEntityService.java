package com.desafio.codenation.services;

import com.desafio.codenation.domain.origin.Origins;
import com.desafio.codenation.domain.security.SecurityEntity;
import com.desafio.codenation.domain.user.User;
import com.desafio.codenation.domain.user.enums.TypeUser;
import com.desafio.codenation.repositories.OriginsRepositorie;
import com.desafio.codenation.repositories.UserRepositorie;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityEntityService implements UserDetailsService {

    private final UserRepositorie userRepositorie;

    private final OriginsRepositorie originsRepositorie;


    public SecurityEntityService(UserRepositorie userRepositorie, OriginsRepositorie originsRepositorie) {
        this.userRepositorie = userRepositorie;
        this.originsRepositorie = originsRepositorie;
    }

    public static Boolean hasGrant(TypeUser typeUser) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority(typeUser.getDesc()));
    }

    public static String authenticatedUsername() {
        try {
            return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
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
                    .perfis(user.getGrants())
                    .build();

        }
        Origins origins = originsRepositorie.findByKeyAndActive(username, true)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encotrado para o parâmetros informados."));

        return SecurityEntity.builder()
                .id(origins.getId())
                .username(origins.getKey())
                .password(origins.getPassword())
                .perfis(origins.getPerfis())
                .build();

    }


}