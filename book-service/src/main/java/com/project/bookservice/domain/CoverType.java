package com.project.bookservice.domain;

public enum CoverType {
    HARD("hard"),
    SOFT("soft");

    private final String value;

    CoverType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
