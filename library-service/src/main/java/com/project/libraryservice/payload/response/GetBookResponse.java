package com.project.libraryservice.payload.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class GetBookResponse extends BaseResponse {

    private String bookId;
    private String title;
    private String description;
}
