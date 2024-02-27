package com.tekion.cricketgame.utils.enums;

public enum Ball {
    DOT_BALL(0),
    ONE_RUN(1),
    TWO_RUNS(2),
    THREE_RUNS(3),
    FOUR_RUNS(4),
    SIX_RUNS(6),
    OUT(0); // For simplicity, treating OUT as 0 runs

    private final int value;

    Ball(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
