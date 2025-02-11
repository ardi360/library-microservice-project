package com.project.commonproto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/library")
@Tag(name = "Library API", description = "Library General Controller")
public class LibraryGeneralController {

    @GetMapping("/sample")
    @Operation(summary = "Get Sample Book", description = "Returns a sample book response")
    public ResponseEntity<String> getSampleBook() {
        return ResponseEntity.ok("This is a sample book response!");
    }
}

