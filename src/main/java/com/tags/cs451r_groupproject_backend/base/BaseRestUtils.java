package com.tags.cs451r_groupproject_backend.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseRestUtils {

    public static ResponseEntity<BaseRestResponse> generateBaseErrorResponseEntity(Exception exception, HttpStatus httpStatus) {
        BaseRestResponse baseRestResponse = new BaseRestResponse();

        baseRestResponse.setStatus(httpStatus.value());
        baseRestResponse.setMessage(exception.getMessage());
        baseRestResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(baseRestResponse, httpStatus);
    }
}
