package com.desafio.codenation.config;

import com.desafio.codenation.domain.security.SecurityEntity;
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
import java.util.HashMap;
import java.util.Map;

import static com.desafio.codenation.config.SecurityConstants.*;

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
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        String bearerToken = TOKEN_PREFIX + token;

        makeSuccesfulAuthenticationResponseBody(response, bearerToken);
    }

    private void makeUnsuccesfulAuthenticationResponseBody(HttpServletResponse response) throws IOException {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message", "Authentication UNSUCCESFUL.");
        response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
        response.getWriter().write(new Gson().toJson(responseMap));

    }

    private void makeSuccesfulAuthenticationResponseBody(HttpServletResponse response, String bearerToken) throws IOException {
        Map<String, String> responseMap = new HashMap<>();
        response.addHeader(HEADER_STRING, bearerToken);
        responseMap.put("message", "Authentication SUCCESFUL.");
        responseMap.put("token_type", TOKEN_PREFIX);
        responseMap.put("access_token", bearerToken);
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        response.getWriter().write(new Gson().toJson(responseMap));

    }


}