package com.tags.cs451r_groupproject_backend.position.rest;

public class InvalidPositionException extends RuntimeException {
    public InvalidPositionException() {
        super("Invalid position object");
    }
}
