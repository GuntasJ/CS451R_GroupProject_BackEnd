package com.tags.cs451r_groupproject_backend.position.rest;

public class PositionAlreadyExistsException extends RuntimeException {
    public PositionAlreadyExistsException(String className) {
        super("Position already exists with (name): " + className);
    }
}
