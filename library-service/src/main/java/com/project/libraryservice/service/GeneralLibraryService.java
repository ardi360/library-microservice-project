package com.project.libraryservice.service;

import com.project.libraryservice.payload.request.NewBookRequest;
import com.project.libraryservice.payload.response.BaseResponse;
import com.project.libraryservice.payload.response.GetBookDetailResponse;
import com.project.libraryservice.payload.response.GetBookResponse;


public interface GeneralLibraryService {

    BaseResponse createNewBook(NewBookRequest newBookRequest);

    GetBookResponse getBook(String bookTitle);

    GetBookDetailResponse getBookDetail(String bookId);
}
