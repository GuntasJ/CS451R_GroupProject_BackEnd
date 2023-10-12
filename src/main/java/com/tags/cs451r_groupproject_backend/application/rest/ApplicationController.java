package com.tags.cs451r_groupproject_backend.application.rest;

import com.tags.cs451r_groupproject_backend.application.model.Application;
import com.tags.cs451r_groupproject_backend.application.model.ApplicationSortingMethod;
import com.tags.cs451r_groupproject_backend.application.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("tags/api/v1")
@AllArgsConstructor
public class ApplicationController {

    private ApplicationService applicationService;

    @GetMapping("/applications")
    @ResponseStatus(HttpStatus.OK)
    public List<Application> retrieveAllApplications() {
        return applicationService.findAll();
    }

    @GetMapping("/applications/sorted/{sort_by}")
    public List<Application> retrieveAllApplicationsSorted(@PathVariable("sort_by") ApplicationSortingMethod sortBy) {
        //todo:
        return null;
    }

    @GetMapping("/applications/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Application retrieveApplication(@PathVariable Long id) {
        return applicationService.findById(id);
    }

    @PostMapping("/applications")
    @ResponseStatus(HttpStatus.CREATED)
    public Application saveApplication(@RequestBody Application application) {
        return applicationService.saveApplication(application);
    }

    @PutMapping("/applications/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Application updateApplication(@RequestBody Application newApplication, @PathVariable Long id) {
        return applicationService.updateApplication(newApplication, id);
    }

    @DeleteMapping("/applications/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
    }

}
