package ru.botalov.SecondAPP.model;

import lombok.Getter;

@Getter
public enum Positions {
    DEV(2.2, false),
    HR(1.2, true),
    TL(2.6, true),
    AL(1.8, false),
    TEST(1.7, false),
    PR(1.5, false);

    private final double positionCoefficient;
    private final boolean isManager;

    Positions(double positionCoefficient, boolean isManager) {
        this.positionCoefficient = positionCoefficient;
        this.isManager = isManager;
    }
}
