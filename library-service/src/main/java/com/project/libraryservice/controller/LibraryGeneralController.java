package com.project.libraryservice.controller;

import com.project.libraryservice.payload.request.BaseRequest;
import com.project.libraryservice.payload.response.BaseResponse;
import com.project.libraryservice.payload.response.ResultStatus;
import com.project.libraryservice.service.GeneralLibraryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    @PostMapping("/data-seed")
    @Operation(summary = "Seed some data into database", description = "seed book and author and publisher databases with initial data")
    public ResponseEntity<BaseResponse> seedDatabaseInitialData(@RequestBody @Valid BaseRequest baseRequest) {
        BaseResponse baseResponse = generalLibraryService.generateInitialData();
        return ResponseEntity.ok(BaseResponse.builder().resultStatus(ResultStatus.SUCCESS).build());
    }
}

