package com.project.bookservice.service;

import com.google.protobuf.Empty;
import com.project.bookservice.client.AuthorClient;
import com.project.bookservice.client.PublisherClient;
import com.proto.book.BookServiceGrpc;
import com.proto.book.SeedDatabaseResponse;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class BookServiceImpl extends BookServiceGrpc.BookServiceImplBase {

    private final AuthorClient authorClient;
    private final PublisherClient publisherClient;

    @Override
    public void seedDatabase(Empty request, StreamObserver<SeedDatabaseResponse> responseObserver) {
        boolean seedingSuccessful = true;

        SeedDatabaseResponse response = SeedDatabaseResponse.newBuilder()
                .setStatus(seedingSuccessful ? SeedDatabaseResponse.Status.SUCCESS : SeedDatabaseResponse.Status.FAILED)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
