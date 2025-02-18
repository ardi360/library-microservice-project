package com.project.libraryservice.controller;

import com.project.libraryservice.payload.request.NewBookRequest;
import com.project.libraryservice.payload.response.BaseResponse;
import com.project.libraryservice.payload.response.GetBookDetailResponse;
import com.project.libraryservice.payload.response.GetBookResponse;
import com.project.libraryservice.payload.response.ResultStatus;
import com.project.libraryservice.service.GeneralLibraryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/library")
@Tag(name = "Library API", description = "Library General Controller")
@RequiredArgsConstructor
public class LibraryGeneralController {

    private final GeneralLibraryService generalLibraryService;

    @PostMapping(value = "/book", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> createNewBook(@RequestBody @Valid NewBookRequest newBookRequest) {
        BaseResponse newBookCreationResponse = generalLibraryService.createNewBook(newBookRequest);
        return ResponseEntity.ok(newBookCreationResponse);
    }

    @GetMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetBookResponse> getBook(@RequestParam(name = "bookTitle") String bookTitle) {
        GetBookResponse getBookResponse = generalLibraryService.getBook(bookTitle);
        getBookResponse.setResultStatus(ResultStatus.SUCCESS);
        return ResponseEntity.ok(getBookResponse);
    }

    @GetMapping(value = "/book-detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetBookDetailResponse> getBookDetail(@RequestParam(name = "bookId") String bookId) {
        GetBookDetailResponse getBookDetailResponse = generalLibraryService.getBookDetail(bookId);
        getBookDetailResponse.setResultStatus(ResultStatus.SUCCESS);
        return ResponseEntity.ok(getBookDetailResponse);
    }

}

