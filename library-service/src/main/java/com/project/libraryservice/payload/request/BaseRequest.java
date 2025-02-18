package com.project.libraryservice.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Data
@NoArgsConstructor
@SuperBuilder
public class BaseRequest {
    private Map<String, Object> additionalInfoRequest;
}
