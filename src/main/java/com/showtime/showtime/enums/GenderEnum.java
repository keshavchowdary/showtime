package com.showtime.showtime.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum GenderEnum {
    MALE, FEMALE;

    @JsonCreator
    public static GenderEnum fromValue(String value) {
        return value != null ? GenderEnum.valueOf(value.toUpperCase()) : null;
    }
}
