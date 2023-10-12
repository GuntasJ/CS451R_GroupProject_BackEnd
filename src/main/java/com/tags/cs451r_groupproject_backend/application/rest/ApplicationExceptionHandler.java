package com.tags.cs451r_groupproject_backend.application.rest;

import com.tags.cs451r_groupproject_backend.base.BaseRestResponse;
import com.tags.cs451r_groupproject_backend.base.BaseRestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<BaseRestResponse> handleNotFoundException(ApplicationNotFoundException e) {
        return BaseRestUtils.generateBaseErrorResponseEntity(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BaseRestResponse> handleBadRequest(Exception e) {
        return BaseRestUtils.generateBaseErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
    }
}
