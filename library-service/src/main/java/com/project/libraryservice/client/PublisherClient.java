package com.project.libraryservice.client;

import com.proto.publisher.GetPublisherByNameRequest;
import com.proto.publisher.PublisherServiceGrpc;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PublisherClient {

    @GrpcClient("publisherService")
    private PublisherServiceGrpc.PublisherServiceBlockingStub publisherServiceBlockingStub;

    public String fetchPublisherIdByName(String publisherName) {
        log.info("going to get publisher id by name {}", publisherName);
        return publisherServiceBlockingStub.getPublisherByName(GetPublisherByNameRequest.newBuilder().setName(publisherName).build()).getId();
    }
}
