package com.project.authorservice;

import com.project.authorservice.model.Author;
import com.project.authorservice.model.ContactInfo;
import com.project.authorservice.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class DataPopulationCommandLineRunner implements CommandLineRunner {

    private final AuthorRepository authorRepository;

    @Override
    public void run(String... args) throws Exception {
        Author author1 = new Author();
        author1.setId(UUID.randomUUID());
        author1.setFirstName("Mamad");
        author1.setLastName("Mamadi");
        author1.setNsid(RandomStringUtils.randomNumeric(8));
        author1.setBirthDate(randomLocalDate());
        author1.setContactInfo(new ContactInfo(
                Arrays.asList("john.doe@example.com", "mamad.doe1234@example.ecom"),
                Arrays.asList("123-456-7890", "782-1234-1234"),
                Arrays.asList("@johndoe", "@mamad")
        ));

        Author author2 = new Author();
        author2.setId(UUID.randomUUID());
        author2.setFirstName("Sakine");
        author2.setLastName("Smith");
        author2.setNsid(RandomStringUtils.randomNumeric(8));
        author2.setBirthDate(randomLocalDate());
        author2.setContactInfo(new ContactInfo(
                Arrays.asList("sakine.smith@example.com", "sakine123@example.com"),
                Arrays.asList("098-765-4321", "123-423412-3213"),
                Arrays.asList("@janesmith", "@sakine")
        ));
        authorRepository.save(author1);
        authorRepository.save(author2);

        System.out.println("Data population complete.");
    }

    private LocalDate randomLocalDate() {
        long minDay = LocalDate.of(1940, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2000, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }
}

