package com.tags.cs451r_groupproject_backend.position.rest;

import com.tags.cs451r_groupproject_backend.position.model.PositionId;

public class PositionAlreadyExistsException extends RuntimeException {
    public PositionAlreadyExistsException(PositionId id) {
        super("Position already exists with (class/type): " + id.getPositionClass() + ", " + id.getPositionType());
    }
}
