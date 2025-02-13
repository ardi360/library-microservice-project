package com.project.libraryservice.service.impl;

import com.project.libraryservice.client.AuthorClient;
import com.project.libraryservice.client.BookClient;
import com.project.libraryservice.client.PublisherClient;
import com.project.libraryservice.payload.request.NewBookRequest;
import com.project.libraryservice.payload.response.BaseResponse;
import com.project.libraryservice.service.GeneralLibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneralLibraryServiceImpl implements GeneralLibraryService {

    private final BookClient bookClient;

    private final AuthorClient authorClient;

    private final PublisherClient publisherClient;

    @Override
    public BaseResponse createNewBook(NewBookRequest newBookRequest) {
        return null;
    }
}
