package com.desafio.codenation.config;

import com.desafio.codenation.domain.security.SecurityEntity;
import com.desafio.codenation.domain.security.SecurityResponseSuccesfulDTO;
import com.desafio.codenation.domain.security.SecurityResponseUnsuccesfulDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.desafio.codenation.constants.SecurityConstants.Keys.HEADER_AUTHORIZATION;
import static com.desafio.codenation.constants.SecurityConstants.Keys.TOKEN_PREFIX;
import static com.desafio.codenation.constants.SecurityConstants.Message.AUTHENTICATION_NOT_SUCCESSFUL;
import static com.desafio.codenation.constants.SecurityConstants.Message.AUTHENTICATION_SUCCESSFUL;
import static com.desafio.codenation.constants.SecurityConstants.Values.EXPIRATION_TIME;
import static com.desafio.codenation.constants.SecurityConstants.Values.TOKEN_SECRET_KEY;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            SecurityEntity securityEntity = new ObjectMapper().readValue(request.getInputStream(), SecurityEntity.class);

            return this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(securityEntity.getUsername(), securityEntity.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        makeUnsuccesfulAuthenticationResponseBody(response);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {
        String username = ((SecurityEntity) authResult.getPrincipal()).getUsername();

        String token = Jwts
                .builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET_KEY)
                .compact();
        String bearerToken = TOKEN_PREFIX + token;

        makeSuccesfulAuthenticationResponseBody(response, bearerToken);
    }

    private void makeUnsuccesfulAuthenticationResponseBody(HttpServletResponse response) throws IOException {
        SecurityResponseUnsuccesfulDTO srud = SecurityResponseUnsuccesfulDTO.builder()
                .message(AUTHENTICATION_NOT_SUCCESSFUL)
                .build();
        response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
        response.getWriter().write(new Gson().toJson(srud));

    }

    private void makeSuccesfulAuthenticationResponseBody(HttpServletResponse response, String bearerToken) throws IOException {
        response.addHeader(HEADER_AUTHORIZATION, bearerToken);

        SecurityResponseSuccesfulDTO srsd = SecurityResponseSuccesfulDTO.builder()
                .message(AUTHENTICATION_SUCCESSFUL)
                .tokenType(TOKEN_PREFIX)
                .accessToken(bearerToken)
                .build();

        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        response.getWriter().write(new Gson().toJson(srsd));

    }
}