package com.project.libraryservice.controller;

import com.project.libraryservice.payload.request.BaseRequest;
import com.project.libraryservice.payload.response.BaseResponse;
import com.project.libraryservice.payload.response.ResultStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/library")
@Tag(name = "Library API", description = "Library General Controller")
public class LibraryGeneralController {

    @PostMapping("/data-seed")
    @Operation(summary = "Seed some data into database", description = "seed book and author and publisher databases with initial data")
    public ResponseEntity<BaseResponse> seedDatabaseInitialData(@RequestBody @Valid BaseRequest baseRequest) {
        return ResponseEntity.ok(BaseResponse.builder().resultStatus(ResultStatus.SUCCESS).build());
    }
}

