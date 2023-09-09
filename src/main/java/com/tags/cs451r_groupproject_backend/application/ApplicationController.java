package com.tags.cs451r_groupproject_backend.application;

import lombok.AllArgsConstructor;
import org.hibernate.bytecode.enhance.spi.EnhancementInfo;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("tags/v1")
@AllArgsConstructor
public class ApplicationController {

    private final ApplicationRepository applicationRepository;
    private final ApplicationModelAssembler applicationModelAssembler;

    @GetMapping("/applications")
    public CollectionModel<EntityModel<Application>> retrieveAllApplications() {
        List<EntityModel<Application>> applicationModels = applicationRepository.findAll().stream()
                .map(applicationModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(applicationModels,
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                ApplicationController.class
                        ).retrieveAllApplications()
                ).withSelfRel());
    }

    @GetMapping("/applications/{id}")
    public EntityModel<Application> retrieveApplication(@PathVariable Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ApplicationNotFoundException(id));

        return applicationModelAssembler.toModel(application);
    }

    @PostMapping("/applications")
    public ResponseEntity<?> saveNewApplication(@RequestBody Application application) {
        EntityModel<Application> entityModel =
                applicationModelAssembler.toModel(applicationRepository.save(application));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/applications/{id}")
    public ResponseEntity<?> replaceApplication(@RequestBody Application newApplication, @PathVariable Long id) {
        Application updatedApplication = applicationRepository.findById(id)
                .map(oldApplication -> {
                    oldApplication.setFirstName(newApplication.getFirstName());
                    oldApplication.setSurname(newApplication.getSurname());
                    return applicationRepository.save(oldApplication);
                })
                .orElseGet(() -> {
                    newApplication.setId(id);
                    return applicationRepository.save(newApplication);
                });
        EntityModel<Application> entityModel = applicationModelAssembler.toModel(updatedApplication);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/applications/{id}")
    public ResponseEntity<?> deleteApplication(@PathVariable Long id) {
        applicationRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
