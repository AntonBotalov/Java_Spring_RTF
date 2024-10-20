package ru.botalov.SecondAPP.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorMessages {
    EMPTY(""),
    UNKNOWN("Не поддерживаемая ошибка"),
    UNSIPPORTED("Произошла непредвиденная ошибка"),
    VALIDATION("Ошибка валидации");

    private final String description;

    ErrorMessages(String description) {
        this.description = description;
    }

    @JsonValue
    public String getName() {
        return description;
    }
}
