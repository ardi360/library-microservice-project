package com.project.libraryservice.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CoverType {
    HARD("HARD"),
    SOFT("SOFT");

    private final String value;
}
