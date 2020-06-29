package com.desafio.codenation.resources;

import com.desafio.codenation.domain.security.SecurityUserDto;
import com.desafio.codenation.resources.interfaces.SecurityResourcesContract;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("login")
public class SecurityResources implements SecurityResourcesContract {

    public void login(SecurityUserDto securityUserDto) {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }

}
