package com.project.libraryservice.service;

import com.project.libraryservice.payload.request.NewBookRequest;
import com.project.libraryservice.payload.response.BaseResponse;

public interface GeneralLibraryService {

    BaseResponse createNewBook(NewBookRequest newBookRequest);
}
