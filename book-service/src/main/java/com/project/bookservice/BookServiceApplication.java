package com.project.bookservice;

import com.proto.author.AuthorRequest;
import com.proto.author.AuthorResponse;
import com.proto.author.AuthorServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookServiceApplication.class, args);
    }

    // just for test
//    @Bean
//    ApplicationRunner clientRunner(@GrpcClient("authorService") AuthorServiceGrpc.AuthorServiceBlockingStub authorServiceBlockingStub) {
//        return args -> {
//            String authorId = "096918ae-d9a9-4498-878b-1948354bb19a";
//
//            // Create the request
//            AuthorRequest request = AuthorRequest.newBuilder()
//                    .setAuthorId(authorId)
//                    .build();
//
//            // Call the author-service
//            AuthorResponse authorResponse = authorServiceBlockingStub.getAuthor(request);
//
//            // Process the response
//            System.out.println("Author Name: " + authorResponse.getFirstName() + " " + authorResponse.getLastName());
//            System.out.println("Emails: " + authorResponse.getContactInfo().getEmailsList());
//            System.out.println("Phone Numbers: " + authorResponse.getContactInfo().getPhoneNumbersList());
//            System.out.println("Instagram Accounts: " + authorResponse.getContactInfo().getInstagramAccountsList());
//        };
//    }
}
