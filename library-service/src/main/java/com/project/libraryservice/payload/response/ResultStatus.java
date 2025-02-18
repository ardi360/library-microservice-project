package com.project.libraryservice.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResultStatus {
    SUCCESS("000", "messages.success"),
    FAILURE("001", "messages.failure"),
    BAD_INPUT("400", "messages.bad_input"),
    INTERNAL_ERROR("500", "messages.internal_error");

    private final String code;
    private String description;

    ResultStatus(String code, String messageKey) {
        this.code = code;
        this.description = messageKey;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
