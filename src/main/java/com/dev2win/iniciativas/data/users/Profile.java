package com.dev2win.iniciativas.data.users;

public enum Profile {
    Estudiante("Estudiante"),
    Profesor("Profesor"),
    Directivo("Directivo");

    private String value;

    Profile(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
