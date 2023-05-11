package com.dev2win.iniciativas.data.users;

public enum Profile {
    ESTUDIANTE("Estudiante"),
    PROFESOR("Profesor"),
    DIRECTIVO("Directivo");

    private String value;

    Profile(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Profile findByValue(String role) {
        Profile response = null;
        for (Profile p : Profile.values()) {
            if (p.getValue().equalsIgnoreCase(role)) {
                response = p;
                break;
            }
        }
        return response;
    }
}
