package com.project.libraryservice.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class NewBookRequest extends BaseRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    private String title;

    @Size(max = 5000, message = "Description cannot exceed 5000 characters")
    private String description;

    @NotNull(message = "Page numbers are required")
    private Integer pageNumbers;

    @NotNull(message = "Cover type is required")
    private CoverType coverType;

    @NotBlank(message = "Author nsid is required")
    private String authorNsid;

    @NotBlank(message = "Publisher name is required")
    private String publisherName;

}
