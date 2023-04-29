package com.dev2win.iniciativas.data.ideas;

public enum State {
    Open("Open"),
    Closed("Closed"),
    Revision("Revision"),
    Supported("Supported"),
    Aproved("Aproved");

    private String value;

    State(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
