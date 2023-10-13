package com.tags.cs451r_groupproject_backend.position.rest;

public class PositionNotFoundException extends RuntimeException {
    public PositionNotFoundException(Long id) {
        super("Could not find position with id: " + id);
    }
}
