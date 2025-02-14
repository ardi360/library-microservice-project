package com.project.authorservice.config;

import com.project.authorservice.model.Author;
import com.project.authorservice.model.ContactInfo;
import com.project.authorservice.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Configuration
@Slf4j
public class AuthorInitialDataPopulate {
    private Faker faker = new Faker();

    @Value("${recordsPopulationCount}")
    private int recordsPopulationCount;

    @Bean
    CommandLineRunner initAuthorData(AuthorRepository authorRepository) {
        return args -> {
            List<Author> authors = new ArrayList<>();
            for (int i = 0; i < recordsPopulationCount; i++) {
                Author author = new Author();
                author.setId(UUID.randomUUID());
                author.setFirstName(faker.name().firstName());
                author.setLastName(faker.name().lastName());
                author.setNsid(faker.number().digits(8));
                author.setBirthDate(faker.timeAndDate().birthday());
                author.setContactInfo(new ContactInfo(
                        Arrays.asList(faker.internet().emailAddress(), faker.internet().emailAddress()),
                        Arrays.asList(faker.phoneNumber().cellPhone(), faker.phoneNumber().cellPhone()),
                        Arrays.asList(faker.internet().username(), faker.internet().username())
                ));
                authors.add(author);
            }

            authorRepository.saveAll(authors);

            log.info("{} sample author records inserted successfully!", recordsPopulationCount);
        };
    }
}
