package com.tags.cs451r_groupproject_backend.student;

import com.tags.cs451r_groupproject_backend.student.model.Student;
import com.tags.cs451r_groupproject_backend.student.rest.StudentController;
import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class StudentModelAssembler implements RepresentationModelAssembler<Student, EntityModel<Student>> {
    @Override
    @NonNull
    public EntityModel<Student> toModel(@NonNull Student student) {
        return EntityModel.of(
                student,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
                        StudentController.class).retrieveStudent(student.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
                        StudentController.class).retrieveAllStudents()).withRel("applications")
        );
    }
}
