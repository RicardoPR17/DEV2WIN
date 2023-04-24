package com.dev2win.iniciativas.data.users;

public enum Role {
    Proponente("Proponente"),
    Administrador("Administrador");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
