package com.dev2win.iniciativas.data.usuarios;

public enum Perfil {
    Estudiante("Estudiante"),
    Profesor("Profesor"),
    Directivo("Directivo");

    private String value;

    Perfil(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
