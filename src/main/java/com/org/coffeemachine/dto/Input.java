package com.org.coffeemachine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Input {
    @JsonProperty("machine")
    private Machine machine;

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }
}
