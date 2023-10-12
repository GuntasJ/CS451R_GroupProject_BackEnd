package com.tags.cs451r_groupproject_backend.administrator.rest;

import com.tags.cs451r_groupproject_backend.administrator.model.Administrator;
import com.tags.cs451r_groupproject_backend.administrator.service.AdministratorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/tags/api/v1")
public class AdministratorController {

    private AdministratorService administratorService;

    @GetMapping("/administrators")
    @ResponseStatus(HttpStatus.OK)
    public List<Administrator> retrieveAllAdministrators() {
        return administratorService.findAll();
    }

    @GetMapping("/administrators/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Administrator retrieveAdministrator(@PathVariable Long id) {
        return administratorService.findById(id);
    }

    @PostMapping("/administrators")
    @ResponseStatus(HttpStatus.CREATED)
    public Administrator saveAdministrator(@RequestBody Administrator administrator) {
        return administratorService.saveAdministrator(administrator);
    }

    @PutMapping("/administrators/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Administrator updateAdministrator(@RequestBody Administrator administrator, @PathVariable Long id) {
        return administratorService.updateAdministrator(administrator, id);
    }

    @DeleteMapping("/administrators/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdministrator(@PathVariable Long id) {
        administratorService.deleteAdministrator(id);
    }

}
