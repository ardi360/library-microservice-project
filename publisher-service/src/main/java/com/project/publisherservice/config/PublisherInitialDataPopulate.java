package com.project.publisherservice.config;

import com.project.publisherservice.domain.AddressInfo;
import com.project.publisherservice.domain.ContactInfo;
import com.project.publisherservice.domain.Publisher;
import com.project.publisherservice.repository.PublisherRepository;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@Slf4j
public class PublisherInitialDataPopulate {

    private Faker faker = new Faker();

    @Value("${recordsPopulationCount}")
    private int recordsPopulationCount;

    @Bean
    CommandLineRunner initPublisherData(PublisherRepository publisherRepository) {
        return args -> {
            List<Publisher> publishers = new ArrayList<>();

            for (int i = 0; i < recordsPopulationCount; i++) {
                Publisher publisher = new Publisher();
                publisher.setName(faker.company().name());
                publisher.setLogoFileUrl(faker.internet().url());

                AddressInfo address = new AddressInfo();
                address.setAddress(faker.address().fullAddress());
                address.setLat(Double.parseDouble(faker.address().latitude()));
                address.setLng(Double.parseDouble(faker.address().longitude()));
                publisher.setAddressInfo(address);

                ContactInfo contact = new ContactInfo();
                contact.setEmails(Arrays.asList(faker.internet().emailAddress(), faker.internet().emailAddress()));
                contact.setPhoneNumbers(Arrays.asList(faker.phoneNumber().cellPhone(), faker.phoneNumber().cellPhone()));
                contact.setInstagramAccounts(Arrays.asList(faker.internet().username(), faker.internet().username()));
                publisher.setContactInfo(contact);

                publishers.add(publisher);
            }

            publisherRepository.saveAll(publishers);

            log.info("{} sample publisher records inserted successfully!", recordsPopulationCount);
        };
    }
}
