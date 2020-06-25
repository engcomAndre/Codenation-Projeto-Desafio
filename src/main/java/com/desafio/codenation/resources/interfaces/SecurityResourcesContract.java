package com.desafio.codenation.resources.interfaces;

import com.desafio.codenation.domain.security.SecurityUserDto;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = {"Login"}, value = "Autenticação", hidden = true, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public interface SecurityResourcesContract {

    @ApiOperation(value = "Login", notes = "Realizar autenticação na API.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(
            @ApiResponse(code = 200, message = "Autenticado com sucesso",
                    responseHeaders = {
                            @ResponseHeader(name = "Authorization", description = "Bearer Token", response = String.class)}))
    @PostMapping
    void login(@RequestBody SecurityUserDto securityUserDto);
}
