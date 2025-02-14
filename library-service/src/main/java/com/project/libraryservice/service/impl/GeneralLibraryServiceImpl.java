package com.project.libraryservice.service.impl;

import com.project.libraryservice.payload.request.NewBookRequest;
import com.project.libraryservice.payload.response.*;
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
    public GetBookResponse getBook(String bookTitle) {
        com.proto.book.GetBookResponse bookFetchResponse = bookServiceBlockingStub.getBook(
                com.proto.book.GetBookRequest.newBuilder()
                        .setTitle(bookTitle)
                        .build()
        );
        return GetBookResponse.builder()
                .bookId(bookFetchResponse.getId())
                .title(bookFetchResponse.getTitle())
                .description(bookFetchResponse.getDescription())
                .build();
    }

    @Override
    public GetBookDetailResponse getBookDetail(String bookId) {
        com.proto.book.GetBookDetailResponse bookDetailRawResponse = bookServiceBlockingStub.getBookDetail(com.proto.book.GetBookDetailRequest.newBuilder().setBookId(bookId).build());

        AuthorResponse author = bookDetailRawResponse.getAuthor();
        PublisherResponse publisher = bookDetailRawResponse.getPublisher();

        GetBookDetailResponse bookDetailResponse = GetBookDetailResponse.builder()
                .bookId(bookId)
                .title(bookDetailRawResponse.getBook().getTitle())
                .description(bookDetailRawResponse.getBook().getDescription())
                .pageNumbers(bookDetailRawResponse.getBook().getPageNumbers())
                .coverType(bookDetailRawResponse.getBook().getCoverType())
                .author(AuthorDetails.builder().firstName(author.getFirstName()).lastName(author.getLastName()).nsid(author.getNsid())
                        .contactInfo(ContactInfo.builder().emails(author.getContactInfo().getEmailsList())
                                .instagramAccounts(author.getContactInfo().getInstagramAccountsList())
                                .phoneNumbers(author.getContactInfo().getPhoneNumbersList()).build())
                        .build())
                .publisher(PublisherDetails.builder().name(publisher.getName()).logoFileUrl(publisher.getLogoFileUrl())
                        .addressInfo(AddressInfo.builder().address(publisher.getAddressInfo().getAddress()).lat(publisher.getAddressInfo().getLat()).lng(publisher.getAddressInfo().getLng()).build())
                        .contactInfo(ContactInfo.builder().emails(publisher.getContactInfo().getEmailsList())
                                .instagramAccounts(publisher.getContactInfo().getInstagramAccountsList())
                                .phoneNumbers(publisher.getContactInfo().getPhoneNumbersList()).build())
                        .build())
                .build();

        return bookDetailResponse;
    }
}
