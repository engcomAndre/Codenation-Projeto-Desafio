package com.desafio.codenation.config;

public class SecurityConstants {
    static final String SECRET = "ChavedeSegurancadoTokenJWT";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    static final long EXPIRATION_TIME = 86400000L;
}