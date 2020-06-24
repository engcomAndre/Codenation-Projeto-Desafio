package com.desafio.codenation.resources;

import com.desafio.codenation.domain.security.SecurityEntity;
import com.desafio.codenation.domain.security.SecurityUserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = {"Login"}, value = "Autenticação", hidden = true, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("login")
public class SecurityResources {

    @ApiOperation(value = "Login", notes = "Realizar autenticação na API.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PostMapping
    private void login(@RequestBody SecurityUserDto securityUserDto) {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }

}
