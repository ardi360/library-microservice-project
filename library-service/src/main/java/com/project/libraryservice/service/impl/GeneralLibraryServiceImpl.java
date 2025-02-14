package com.project.libraryservice.service.impl;

import com.project.libraryservice.payload.request.GetBookRequest;
import com.project.libraryservice.payload.request.NewBookRequest;
import com.project.libraryservice.payload.response.BaseResponse;
import com.project.libraryservice.payload.response.GetBookResponse;
import com.project.libraryservice.payload.response.ResultStatus;
import com.project.libraryservice.service.GeneralLibraryService;
import com.proto.author.AuthorResponse;
import com.proto.author.AuthorServiceGrpc;
import com.proto.author.GetAuthorIdByNsidRequest;
import com.proto.book.BookServiceGrpc;
import com.proto.book.CreateNewBookRequest;
import com.proto.book.CreateNewBookResponse;
import com.proto.publisher.GetPublisherIdByNameRequest;
import com.proto.publisher.PublisherResponse;
import com.proto.publisher.PublisherServiceGrpc;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneralLibraryServiceImpl implements GeneralLibraryService {

    @GrpcClient("authorService")
    private AuthorServiceGrpc.AuthorServiceBlockingStub authorServiceBlockingStub;

    @GrpcClient("publisherService")
    private PublisherServiceGrpc.PublisherServiceBlockingStub publisherServiceBlockingStub;

    @GrpcClient("bookService")
    private BookServiceGrpc.BookServiceBlockingStub bookServiceBlockingStub;


    @Override
    public BaseResponse createNewBook(NewBookRequest newBookRequest) {
        AuthorResponse authorByNsid = authorServiceBlockingStub.getAuthorIdByNsid(
                GetAuthorIdByNsidRequest.newBuilder()
                        .setNsid(newBookRequest.getAuthorNsid())
                        .build());
        PublisherResponse publisherByName = publisherServiceBlockingStub.getPublisherIdByName(
                GetPublisherIdByNameRequest.newBuilder()
                        .setName(newBookRequest.getPublisherName())
                        .build());
        CreateNewBookResponse newBookCreationResponse = bookServiceBlockingStub.createNewBook(
                CreateNewBookRequest.newBuilder()
                        .setTitle(newBookRequest.getTitle())
                        .setDescription(newBookRequest.getDescription())
                        .setPageNumbers(newBookRequest.getPageNumbers())
                        .setCoverType(newBookRequest.getCoverType().getValue())
                        .setAuthorId(authorByNsid.getId())
                        .setPublicationId(publisherByName.getId())
                        .build());
        return BaseResponse.builder()
                .resultStatus(newBookCreationResponse.getStatus().equals(CreateNewBookResponse.Status.SUCCESS) ? ResultStatus.SUCCESS : ResultStatus.FAILURE)
                .build();
    }

    @Override
    public GetBookResponse getBook(GetBookRequest getBookRequest) {
        com.proto.book.GetBookResponse bookFetchResponse = bookServiceBlockingStub.getBook(
                com.proto.book.GetBookRequest.newBuilder()
                        .setTitle(getBookRequest.getTitle())
                        .build()
        );
        return GetBookResponse.builder()
                .bookId(bookFetchResponse.getId())
                .title(bookFetchResponse.getTitle())
                .description(bookFetchResponse.getDescription())
                .build();
    }
}
