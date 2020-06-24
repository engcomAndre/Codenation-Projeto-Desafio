package com.desafio.codenation.config;

public class SecurityConstants {
    static final String SECRET = "Chave de Segurança do Token JWT";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    static final long EXPIRATION_TIME = 86400000L;
}