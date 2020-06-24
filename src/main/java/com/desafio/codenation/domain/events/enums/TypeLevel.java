package com.desafio.codenation.domain.events.enums;

public enum TypeLevel{

    ERROR(0,"ERROR"),WARNING(1, "WARNING"), INFO(2, "INFO");

    private int cod;
    private String desc;

    private TypeLevel(int cod, String desc) {
        this.cod = cod;
        this.desc = desc;
    }

    public int getCod() {
        return cod;
    }

    public String getDesc() {
        return desc;
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
        throw new IllegalArgumentException("Id Inválido" + cod);
    }
}