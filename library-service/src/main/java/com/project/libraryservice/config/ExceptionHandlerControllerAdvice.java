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
public class ExceptionHandlerControllerAdvice {

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponse> IllegalArgumentExceptionHandler(IllegalArgumentException illegalArgumentException) {
        String exceptionMessage = illegalArgumentException.getMessage();
        log.error("illegalArgumentException Exception Raised and Caught By ExceptionHandlerControllerAdvice: " + exceptionMessage);
        return ResponseEntity.badRequest().body(BaseResponse.builder().resultStatus(ResultStatus.BAD_INPUT).furtherDescription(exceptionMessage).build());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> ExceptionHandler(Exception exception) {
        String exceptionMessage = exception.getMessage();
        log.error("Unknown Exception Raised and Caught By ExceptionHandlerControllerAdvice: " + exceptionMessage);
        return ResponseEntity.internalServerError().body(BaseResponse.builder().resultStatus(ResultStatus.INTERNAL_ERROR).furtherDescription(exceptionMessage).build());
    }
}
