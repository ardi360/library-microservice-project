package com.project.bookservice.client;

import com.proto.publisher.PublisherRequest;
import com.proto.publisher.PublisherResponse;
import com.proto.publisher.PublisherServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class PublisherClient {
    @GrpcClient("publisherService")
    private PublisherServiceGrpc.PublisherServiceBlockingStub publisherServiceBlockingStub;

    public void fetchPublisherData(String publisherId) {
        PublisherRequest publisherRequest = PublisherRequest.newBuilder()
                .setPublisherId(publisherId)
                .build();
        PublisherResponse publisherResponse = publisherServiceBlockingStub.getPublisher(publisherRequest);

        System.out.println("Publisher Name: " + publisherResponse.getName());
        System.out.println("Address: " + publisherResponse.getAddressInfo().getAddress());
        System.out.println("Emails: " + publisherResponse.getContactInfo().getEmailsList());
        System.out.println("Phone Numbers: " + publisherResponse.getContactInfo().getPhoneNumbersList());
        System.out.println("Instagram Accounts: " + publisherResponse.getContactInfo().getInstagramAccountsList());
    }

}
