package com.project.libraryservice.config;


import com.project.libraryservice.payload.response.BaseResponse;
import com.project.libraryservice.payload.response.ResultStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class GeneralExceptionHandler {

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponse> generalExceptionHandler(IllegalArgumentException illegalArgumentException) {
        log.error("illegalArgumentException Exception Raised and Caught By ControllerAdvice: " + illegalArgumentException.getMessage());
        return ResponseEntity.badRequest().body(BaseResponse.builder().resultStatus(ResultStatus.BAD_INPUT).build());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> generalExceptionHandler(Exception exception) {
        log.error("internal server Exception Raised and Caught By ControllerAdvice: " + exception.getMessage());
        return ResponseEntity.internalServerError().body(BaseResponse.builder().resultStatus(ResultStatus.INTERNAL_ERROR).build());
    }
}
