package com.project.bookservice.client;

import com.proto.author.AuthorRequest;
import com.proto.author.AuthorResponse;
import com.proto.author.AuthorServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class AuthorClient {
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

