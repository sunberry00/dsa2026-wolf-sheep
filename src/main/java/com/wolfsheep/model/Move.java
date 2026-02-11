package com.wolfsheep.model;

public class Move {
    private int from;
    private int to;

    public Move(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public static Move wolfMove(int to) {
        return new Move(-1, to);
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }
}
