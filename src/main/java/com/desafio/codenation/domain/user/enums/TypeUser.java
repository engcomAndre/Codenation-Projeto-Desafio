package com.desafio.codenation.domain.user.enums;

public enum TypeUser {

    UNDEFINED(0, "ROLE_UNDEFINED"), ADMIN(1, "ROLE_ADMIN"), COMMON_USER(2, "ROLE_COMMOM_USER");

    private int cod;
    private String desc;

    TypeUser(int cod, String desc) {
        this.cod = cod;
        this.desc = desc;
    }

    public static TypeUser toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (TypeUser t : TypeUser.values()) {
            if (cod.equals(t.getCod())) {
                return t;
            }
        }
        throw new IllegalArgumentException("Id Inválido" + cod);

    }

    public int getCod() {
        return cod;
    }

    public String getDesc() {
        return desc;
    }
}
