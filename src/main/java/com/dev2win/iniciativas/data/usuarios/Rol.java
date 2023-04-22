package com.dev2win.iniciativas.data.usuarios;

public enum Rol {
    Proponente("Proponente"),
    Administrador("Administrador");

    private String value;

    Rol(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
