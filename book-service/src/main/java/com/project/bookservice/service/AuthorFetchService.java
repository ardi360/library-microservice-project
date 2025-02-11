package com.project.bookservice.service;

import com.proto.author.AuthorServiceGrpc;
import com.proto.author.AuthorRequest;
import com.proto.author.AuthorResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class AuthorFetchService {
    @GrpcClient("authorService")
    private AuthorServiceGrpc.AuthorServiceBlockingStub authorServiceBlockingStub;

    public void fetchAuthorData(String authorId) {
        AuthorRequest request = AuthorRequest.newBuilder()
                .setAuthorId(authorId)
                .build();

        AuthorResponse authorResponse = authorServiceBlockingStub.getAuthor(request);

        // Process the response
        System.out.println("Author Name: " + authorResponse.getFirstName() + " " + authorResponse.getLastName());
        System.out.println("Emails: " + authorResponse.getContactInfo().getEmailsList());
        System.out.println("Phone Numbers: " + authorResponse.getContactInfo().getPhoneNumbersList());
        System.out.println("Instagram Accounts: " + authorResponse.getContactInfo().getInstagramAccountsList());
    }
}

