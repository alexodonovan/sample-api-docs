package com.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UnknownEircode extends Eircode {

    private static final String EMPTY_STRING = "";

    public UnknownEircode() {
        this.routingKey = EMPTY_STRING;
        this.identifier = EMPTY_STRING;
    }

    @JsonIgnore
    @Override
    public boolean exists() {
        return false;
    }

}
