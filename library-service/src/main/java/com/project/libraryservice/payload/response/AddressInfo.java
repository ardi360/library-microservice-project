package com.project.libraryservice.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class AddressInfo {
    private String address;
    private Double lat;
    private Double lng;
}
