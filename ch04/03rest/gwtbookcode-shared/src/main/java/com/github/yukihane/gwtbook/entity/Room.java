package com.github.yukihane.gwtbook.entity;

public enum Room {

    A("A組"), B("B組"), C("C組");

    private final String displayName;

    private Room(final String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
