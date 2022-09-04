package com.vmware.talentboost.controller;

import com.vmware.talentboost.dto.ErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(value = { NoSuchElementException.class })
    protected ResponseEntity<ErrorDto> handleNotFound(final RuntimeException ex,
                                                      final WebRequest request) {
        final ErrorDto error = new ErrorDto();
        error.message = ex.getMessage();
        error.statusCode = HttpStatus.NOT_FOUND.value();
        return new ResponseEntity<>(error, new HttpHeaders(),
                HttpStatus.NOT_FOUND);
    }

}
