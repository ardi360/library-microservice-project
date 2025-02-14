package com.project.libraryservice.service;

import com.project.libraryservice.payload.request.GetBookDetailRequest;
import com.project.libraryservice.payload.request.GetBookRequest;
import com.project.libraryservice.payload.request.NewBookRequest;
import com.project.libraryservice.payload.response.BaseResponse;
import com.project.libraryservice.payload.response.GetBookDetailResponse;
import com.project.libraryservice.payload.response.GetBookResponse;


public interface GeneralLibraryService {

    BaseResponse createNewBook(NewBookRequest newBookRequest);

    GetBookResponse getBook(GetBookRequest getBookRequest);

    GetBookDetailResponse getBookDetail(GetBookDetailRequest getBookDetailRequest);
}
