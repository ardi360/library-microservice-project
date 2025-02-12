package com.project.libraryservice.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResultStatus {
        SUCCESS("000", "Successful Operation"),
        FAILURE("001", "Operation Failed"),
        BAD_INPUT("401", "Invalid Input Provided"),
        INTERNAL_ERROR("501", "Internal Error");

        private final String code;
        private final String description;
}
