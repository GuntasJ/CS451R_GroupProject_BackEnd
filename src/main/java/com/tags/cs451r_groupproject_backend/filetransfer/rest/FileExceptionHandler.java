package com.tags.cs451r_groupproject_backend.filetransfer.rest;

import com.tags.cs451r_groupproject_backend.base.BaseRestResponse;
import com.tags.cs451r_groupproject_backend.base.BaseRestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class FileExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<BaseRestResponse> handleSizeException(MaxUploadSizeExceededException e) {
        return BaseRestUtils.generateBaseErrorResponseEntity(e, HttpStatus.EXPECTATION_FAILED);
    }


}
