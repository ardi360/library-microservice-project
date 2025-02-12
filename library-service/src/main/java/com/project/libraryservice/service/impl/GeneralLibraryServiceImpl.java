package com.project.libraryservice.service.impl;

import com.project.libraryservice.client.BookClient;
import com.project.libraryservice.payload.response.BaseResponse;
import com.project.libraryservice.service.GeneralLibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneralLibraryServiceImpl implements GeneralLibraryService {

    private final BookClient bookClient;

    @Override
    public BaseResponse generateInitialData() {
        bookClient.dataSeed();
        return null;
    }
}
