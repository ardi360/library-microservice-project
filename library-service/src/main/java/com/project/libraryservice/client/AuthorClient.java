package com.project.libraryservice.client;

import com.proto.author.AuthorServiceGrpc;
import com.proto.author.GetAuthorByNsidRequest;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthorClient {

    @GrpcClient("authorService")
    private AuthorServiceGrpc.AuthorServiceBlockingStub authorServiceBlockingStub;

    public String fetchAuthorIdByNsid(String authorNsid) {
        log.info("going to get author id by authorNsid {}", authorNsid);
        return authorServiceBlockingStub.getAuthorByNsid(GetAuthorByNsidRequest.newBuilder().setNsid(authorNsid).build()).getId();
    }
}
