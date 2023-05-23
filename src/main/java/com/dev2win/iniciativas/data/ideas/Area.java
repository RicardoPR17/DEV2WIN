package com.dev2win.iniciativas.data.ideas;

import javax.print.DocFlavor.STRING;

public enum Area {
    ENVIRONMENT("Environment"),
    IA("Articial Intelligence"),
    UNDERTAKING("Undertaking"),
    ANIMALS("Animal rights"),
    HEALTHY("Healthy life");

    private String value;
    
    Area(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Area findByValue(String area) {
        Area response = null;
        for (Area r : Area.values()) {
            if (r.getValue().equalsIgnoreCase(area)) {
                response = r;
                break;
            }
        }
        return response;
    }
}
