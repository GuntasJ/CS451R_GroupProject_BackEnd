package com.tags.cs451r_groupproject_backend.administrator.rest;

import com.tags.cs451r_groupproject_backend.base.BaseRestResponse;
import com.tags.cs451r_groupproject_backend.base.BaseRestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdministratorExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<BaseRestResponse> handleNotFoundException(AdministratorNotFoundException e) {
        return BaseRestUtils.generateBaseErrorResponseEntity(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BaseRestResponse> handleBadRequestException(Exception e) {
        return BaseRestUtils.generateBaseErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
    }


}
