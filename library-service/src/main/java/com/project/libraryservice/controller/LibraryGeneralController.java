package com.project.libraryservice.controller;

import com.project.libraryservice.payload.request.NewBookRequest;
import com.project.libraryservice.payload.response.BaseResponse;
import com.project.libraryservice.payload.response.ResultStatus;
import com.project.libraryservice.service.GeneralLibraryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/library")
@Tag(name = "Library API", description = "Library General Controller")
@RequiredArgsConstructor
public class LibraryGeneralController {

    private final GeneralLibraryService generalLibraryService;

    @PostMapping(value = "/book", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> createNewBook(@RequestBody @Valid NewBookRequest newBookRequest) {
        BaseResponse newBookCreationResponse = generalLibraryService.createNewBook(newBookRequest);
        return ResponseEntity.ok(BaseResponse.builder().resultStatus(ResultStatus.SUCCESS).build());
    }

}

