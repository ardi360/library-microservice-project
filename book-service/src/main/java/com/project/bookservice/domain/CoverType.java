package com.project.bookservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CoverType {
    HARD("HARD"),
    SOFT("SOFT");

    private final String value;
}
