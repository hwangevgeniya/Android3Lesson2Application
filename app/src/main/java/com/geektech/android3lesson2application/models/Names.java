package com.geektech.android3lesson2application.models;

import java.io.Serializable;

public class Names implements Serializable {

    String names;

    public Names(String names) {
        this.names = names;
    }

    public Names() {
    }

    public String getNames() {
        return names;
    }

    public Names setNames(String names) {
        this.names = names;
        return null;
    }
}
