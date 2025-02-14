package com.project.libraryservice.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class AuthorDetails {
    private String firstName;
    private String lastName;
    private String nsid;
    private ContactInfo contactInfo;
}
