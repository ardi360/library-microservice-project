package com.project.authorservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfo {
    private List<String> emails;
    private List<String> phoneNumbers;
    private List<String> instagramAccounts;
}
