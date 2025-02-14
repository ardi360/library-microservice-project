package com.project.libraryservice.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class PublisherDetails {
    private String name;
    private String logoFileUrl;
    private AddressInfo addressInfo;
    private ContactInfo contactInfo;
}
