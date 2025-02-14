package com.project.libraryservice.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class GetBookDetailRequest extends BaseRequest {

    @NotBlank(message = "BookId is required")
    private String bookId;
}
