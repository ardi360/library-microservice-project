package com.project.publisherservice.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressInfo {
    private String address;
    private Double lat;
    private Double lng;
}
