package com.dev2win.iniciativas.data.ideas;

public enum State {
    Open("Open"),
    Closed("Closed"),
    Revision("Revision"),
    Aproved("Aproved");

    private String value;

    State(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static State findByValue(String state) {
        State response = null;
        for (State r : State.values()) {
            if (r.getValue().equalsIgnoreCase(state)) {
                response = r;
                break;
            }
        }
        return response;
    }
}
