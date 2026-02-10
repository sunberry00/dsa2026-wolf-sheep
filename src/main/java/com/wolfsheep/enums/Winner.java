package com.wolfsheep.enums;

public enum Winner {
    NONE("None"),
    WOLF("Wolf"),
    SHEEP("Sheep");

    public final String label;

    private Winner(String label) {
        this.label = label;
    }
}
