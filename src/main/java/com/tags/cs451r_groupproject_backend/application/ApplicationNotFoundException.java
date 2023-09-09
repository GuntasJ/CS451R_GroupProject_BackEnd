package com.tags.cs451r_groupproject_backend.application;

public class ApplicationNotFoundException extends RuntimeException {
    public ApplicationNotFoundException(Long id) {
        super("Could not find application with id: " + id);
    }
}
