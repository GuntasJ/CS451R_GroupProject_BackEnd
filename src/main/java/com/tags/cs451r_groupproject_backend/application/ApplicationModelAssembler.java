package com.tags.cs451r_groupproject_backend.application;

import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component

public class ApplicationModelAssembler implements RepresentationModelAssembler<Application, EntityModel<Application>> {

    @Override
    @NonNull
    public EntityModel<Application> toModel(@NonNull Application application) {
        return EntityModel.of(
                application,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
                        ApplicationController.class).retrieveApplication(application.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
                        ApplicationController.class).retrieveAllApplications()).withRel("applications")
        );
    }
}
