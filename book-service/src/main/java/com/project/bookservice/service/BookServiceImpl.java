package com.project.bookservice.service;

import com.project.bookservice.domain.Book;
import com.project.bookservice.domain.CoverType;
import com.project.bookservice.repository.BookRepository;
import com.proto.author.AuthorResponse;
import com.proto.author.AuthorServiceGrpc;
import com.proto.author.GetAuthorByIdRequest;
import com.proto.book.*;
import com.proto.publisher.GetPublisherByIdRequest;
import com.proto.publisher.PublisherResponse;
import com.proto.publisher.PublisherServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Optional;
import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class BookServiceImpl extends BookServiceGrpc.BookServiceImplBase {

    private final BookRepository bookRepository;
    @GrpcClient("authorService")
    private AuthorServiceGrpc.AuthorServiceBlockingStub authorServiceBlockingStub;
    @GrpcClient("publisherService")
    private PublisherServiceGrpc.PublisherServiceBlockingStub publisherServiceBlockingStub;

    @Override
    public void createNewBook(CreateNewBookRequest request, StreamObserver<CreateNewBookResponse> responseObserver) {
        try {
            bookRepository.save(
                    Book.builder()
                            .title(request.getTitle())
                            .description(request.getDescription())
                            .pageNumbers(request.getPageNumbers())
                            .coverType(CoverType.valueOf(request.getCoverType()))
                            .authorId(UUID.fromString(request.getAuthorId()))
                            .publicationId(UUID.fromString(request.getPublicationId()))
                            .build()
            );

            responseObserver.onNext(CreateNewBookResponse.newBuilder().setStatusValue(0).build());
            responseObserver.onCompleted();
        } catch (Exception exception) {
            responseObserver.onError(new RuntimeException("an Error Occurred During save the entity"));
        }
    }

    @Override
    public void getBook(GetBookRequest request, StreamObserver<GetBookResponse> responseObserver) {
        Optional<Book> bookByTitleIdOptional = bookRepository.findByTitle(request.getTitle());

        if (bookByTitleIdOptional.isPresent()) {
            Book book = bookByTitleIdOptional.get();

            GetBookResponse response = GetBookResponse.newBuilder()
                    .setId(book.getId().toString())
                    .setTitle(book.getTitle())
                    .setDescription(book.getDescription())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new RuntimeException("Book not found"));
        }
    }

    @Override
    public void getBookDetail(GetBookDetailRequest request, StreamObserver<GetBookDetailResponse> responseObserver) {
        Optional<Book> foundedBookByIdOptional = bookRepository.findById(UUID.fromString(request.getBookId()));

        if (foundedBookByIdOptional.isPresent()) {
            Book book = foundedBookByIdOptional.get();
            AuthorResponse authorResponse = authorServiceBlockingStub.getAuthorById(GetAuthorByIdRequest.newBuilder().setAuthorId(book.getAuthorId().toString()).build());
            PublisherResponse publisherResponse = publisherServiceBlockingStub.getPublisherById(GetPublisherByIdRequest.newBuilder().setPublisherId(book.getPublicationId().toString()).build());

            GetBookDetailResponse response = GetBookDetailResponse.newBuilder()
                    .setBook(GetBookDetailResponse.Book.newBuilder()
                            .setTitle(book.getTitle())
                            .setDescription(book.getDescription())
                            .setPageNumbers(book.getPageNumbers())
                            .setCoverType(book.getCoverType().getValue())
                            .build())
                    .setAuthor(authorResponse)
                    .setPublisher(publisherResponse)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new RuntimeException("Book not found"));
        }
    }
}
