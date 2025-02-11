package com.project.publisherservice.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import java.util.List;

import jakarta.persistence.FetchType;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactInfo {

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> emails;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> phoneNumbers;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> instagramAccounts;
}

