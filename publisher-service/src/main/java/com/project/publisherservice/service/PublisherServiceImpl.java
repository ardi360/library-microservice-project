package com.project.publisherservice.service;

import com.project.publisherservice.domain.Publisher;
import com.project.publisherservice.repository.PublisherRepository;
import com.proto.publisher.*;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Optional;
import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class PublisherServiceImpl extends PublisherServiceGrpc.PublisherServiceImplBase {

    private final PublisherRepository publisherRepository;

    @Override
    public void getPublisherById(GetPublisherByIdRequest request, StreamObserver<PublisherResponse> responseObserver) {
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
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new RuntimeException("Publisher not found"));
        }
    }

    @Override
    public void getPublisherIdByName(GetPublisherIdByNameRequest request, StreamObserver<PublisherResponse> responseObserver) {
        String publisherName = request.getName();
        Optional<Publisher> publisherByIdOptional = publisherRepository.findByName(publisherName);

        if (publisherByIdOptional.isPresent()) {
            Publisher publisher = publisherByIdOptional.get();

            PublisherResponse response = PublisherResponse.newBuilder()
                    .setId(publisher.getId().toString())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new RuntimeException("Publisher not found"));
        }
    }

}
