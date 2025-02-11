package com.project.publisherservice.service;

import com.project.publisherservice.domain.Publisher;
import com.project.publisherservice.repository.PublisherRepository;
import com.proto.publisher.*;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class PublisherServiceImpl extends PublisherServiceGrpc.PublisherServiceImplBase {

    private final PublisherRepository publisherRepository;

    @Override
    public void getPublisher(PublisherRequest request, StreamObserver<PublisherResponse> responseObserver) {
        UUID publisherId = UUID.fromString(request.getPublisherId());
        Optional<Publisher> publisherOptional = publisherRepository.findById(publisherId);

        if (publisherOptional.isPresent()) {
            Publisher publisher = publisherOptional.get();

            PublisherResponse response = PublisherResponse.newBuilder()
                    .setId(publisher.getId().toString())
                    .setName(publisher.getName())
                    .setLogoFileUrl(publisher.getLogoFileUrl())
                    .setAddressInfo(AddressInfo.newBuilder()
                            .setAddress(publisher.getAddressInfo().getAddress())
                            .setLat(publisher.getAddressInfo().getLat())
                            .setLng(publisher.getAddressInfo().getLng())
                            .build())
                    .setContactInfo(ContactInfo.newBuilder()
                            .addAllEmails(publisher.getContactInfo().getEmails())
                            .addAllPhoneNumbers(publisher.getContactInfo().getPhoneNumbers())
                            .addAllInstagramAccounts(publisher.getContactInfo().getInstagramAccounts())
                            .build())
                    .setCreatedAt(publisher.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME))
                    .setUpdatedAt(publisher.getUpdatedAt().format(DateTimeFormatter.ISO_DATE_TIME))
                    .setDeletedAt(publisher.getDeletedAt() != null ?
                            publisher.getDeletedAt().format(DateTimeFormatter.ISO_DATE_TIME) : null)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new RuntimeException("Publisher not found"));
        }
    }
}
