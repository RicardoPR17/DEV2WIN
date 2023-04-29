package com.dev2win.iniciativas.data.ideas;

public enum StateInitiative {
    
    Created("Created"),
    Acepted("Acepted");

    private String value;

    StateInitiative(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
