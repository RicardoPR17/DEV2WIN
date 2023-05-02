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

    public static Role findByValue(String role) {
        Role response = null;
        for (Role r : Role.values()) {
            if (r.getValue().equalsIgnoreCase(role)) {
                response = r;
                break;
            }
        }
        return response;
    }
}
