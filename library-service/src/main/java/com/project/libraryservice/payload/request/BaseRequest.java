package com.project.libraryservice.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class BaseRequest {
    @NotNull
    private Integer requestNo;
}
