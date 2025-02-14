package com.project.authorservice.service;

import com.project.authorservice.model.Author;
import com.project.authorservice.repository.AuthorRepository;
import com.proto.author.*;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class AuthorServiceImpl extends AuthorServiceGrpc.AuthorServiceImplBase {

    private final AuthorRepository authorRepository;

    @Override
    public void getAuthorById(GetAuthorByIdRequest request, StreamObserver<AuthorResponse> responseObserver) {
        UUID authorId = UUID.fromString(request.getAuthorId());
        Optional<Author> authorOptional = authorRepository.findById(authorId);

        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();

            AuthorResponse response = AuthorResponse.newBuilder()
                    .setId(author.getId().toString())
                    .setFirstName(author.getFirstName())
                    .setLastName(author.getLastName())
                    .setNsid(author.getNsid())
                    .setBirthDate(author.getBirthDate().format(DateTimeFormatter.ISO_DATE))
                    .setContactInfo(ContactInfo.newBuilder()
                            .addAllEmails(author.getContactInfo().getEmails())
                            .addAllPhoneNumbers(author.getContactInfo().getPhoneNumbers())
                            .addAllInstagramAccounts(author.getContactInfo().getInstagramAccounts())
                            .build())
                    .setCreatedAt(author.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME))
                    .setUpdatedAt(author.getUpdatedAt().format(DateTimeFormatter.ISO_DATE_TIME))
                    .setDeletedAt(author.getDeletedAt() != null ?
                            author.getDeletedAt().format(DateTimeFormatter.ISO_DATE_TIME) : null)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new RuntimeException("Author not found"));
        }
    }

    @Override
    public void getAuthorIdByNsid(GetAuthorIdByNsidRequest request, StreamObserver<AuthorResponse> responseObserver) {
        String authorNsid = request.getNsid();
        Optional<Author> authorByNsid = authorRepository.findByNsid(authorNsid);
        if (authorByNsid.isPresent()) {
            Author author = authorByNsid.get();

            AuthorResponse response = AuthorResponse.newBuilder()
                    .setId(author.getId().toString())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new RuntimeException("Author not found"));
        }
    }
}