package com.project.publisherservice;

import com.project.publisherservice.domain.AddressInfo;
import com.project.publisherservice.domain.ContactInfo;
import com.project.publisherservice.domain.Publisher;
import com.project.publisherservice.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class PublisherDataSeeder {

    @Bean
    CommandLineRunner initDatabase(PublisherRepository publisherRepository) {
        return args -> {
            Publisher publisher1 = new Publisher();
            publisher1.setName("TechBooks Publishing");
            publisher1.setLogoFileUrl("https://example.com/logo1.png");

            AddressInfo address1 = new AddressInfo();
            address1.setAddress("123 Tech Street, Silicon Valley");
            address1.setLat(37.7749);
            address1.setLng(-122.4194);
            publisher1.setAddressInfo(address1);

            ContactInfo contact1 = new ContactInfo();
            contact1.setEmails(Arrays.asList("contact@techbooks.com", "info@techbooks.com"));
            contact1.setPhoneNumbers(Arrays.asList("+1234567890", "+0987654321"));
            contact1.setInstagramAccounts(Arrays.asList("@techbooks", "@publishingworld"));
            publisher1.setContactInfo(contact1);

            Publisher publisher2 = new Publisher();
            publisher2.setName("FutureReads Inc.");
            publisher2.setLogoFileUrl("https://example.com/logo2.png");

            AddressInfo address2 = new AddressInfo();
            address2.setAddress("456 Innovation Ave, New York");
            address2.setLat(40.7128);
            address2.setLng(-74.0060);
            publisher2.setAddressInfo(address2);

            ContactInfo contact2 = new ContactInfo();
            contact2.setEmails(Arrays.asList("support@futurereads.com"));
            contact2.setPhoneNumbers(Arrays.asList("+1987654321"));
            contact2.setInstagramAccounts(Arrays.asList("@futurereads"));
            publisher2.setContactInfo(contact2);

            publisherRepository.save(publisher1);
            publisherRepository.save(publisher2);

            System.out.println("✅ Sample publishers data inserted successfully!");
        };
    }
}
