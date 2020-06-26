package com.desafio.codenation.domain.events.enums;

public enum TypeLevel {

    ERROR(1, "ERROR"), WARNING(2, "WARNING"), INFO(3, "INFO");

    private final int cod;
    private final String desc;

    TypeLevel(int cod, String desc) {
        this.cod = cod;
        this.desc = desc;
    }

    public static TypeLevel toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (TypeLevel t : TypeLevel.values()) {
            if (cod.equals(t.getCod())) {
                return t;
            }
        }
        throw new IllegalArgumentException("Invalid Id " + cod);
    }

    public int getCod() {
        return cod;
    }

    public String getDesc() {
        return desc;
    }
}