package com.tags.cs451r_groupproject_backend.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaseRestResponse {
    private Integer status;
    private String message;
    private Long timeStamp;
}
