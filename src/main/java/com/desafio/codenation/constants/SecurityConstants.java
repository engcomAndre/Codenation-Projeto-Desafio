package com.desafio.codenation.constants;

public class SecurityConstants {

    public static class Values{
        public static Long EXPIRATION_TIME = 86400000L;
        public static String TOKEN_SECRET_KEY = "ChavedeSegurancadoTokenJWT";
    }

    public static class Keys {
        public static String TOKEN_PREFIX = "Bearer";
        public static String HEADER_AUTHORIZATION = "Authorization";
    }

    public static class Message {
        public static String AUTHENTICATION_SUCCESSFUL = "Autenticação bem sucedida.";
        public static String AUTHENTICATION_NOT_SUCCESSFUL = "Problemas na Autenticação.";

    }
}
