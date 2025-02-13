package com.project.libraryservice.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CoverType {
    HARD("Hard"),
    SOFT("Soft");

    private final String value;
}
