package com.project.libraryservice.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
public class ContactInfo {
    private List<String> emails;
    private List<String> phoneNumbers;
    private List<String> instagramAccounts;
}
