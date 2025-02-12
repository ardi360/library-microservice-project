package com.project.libraryservice.client;

import com.google.protobuf.Empty;
import com.proto.book.BookServiceGrpc;
import com.proto.book.SeedDatabaseResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class BookClient {
    @GrpcClient("bookService")
    private BookServiceGrpc.BookServiceBlockingStub bookServiceBlockingStub;

    public void dataSeed() {
        SeedDatabaseResponse seedDatabaseResponse = bookServiceBlockingStub.seedDatabase(Empty.getDefaultInstance());

        System.out.println("result is: " + seedDatabaseResponse.getStatus());
    }
}