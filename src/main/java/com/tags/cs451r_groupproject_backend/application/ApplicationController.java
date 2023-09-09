package com.tags.cs451r_groupproject_backend.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tags/v1")
public class ApplicationController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
