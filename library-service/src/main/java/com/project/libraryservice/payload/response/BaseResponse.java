package com.project.libraryservice.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Data
@NoArgsConstructor
@SuperBuilder
public class BaseResponse {
    private ResultStatus resultStatus;
    private String furtherDescription;
    private Map<String, Object> additionalInfoRequest;
}
