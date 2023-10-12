package com.tags.cs451r_groupproject_backend.application.service;

import com.tags.cs451r_groupproject_backend.application.model.Application;
import com.tags.cs451r_groupproject_backend.application.rest.ApplicationNotFoundException;
import com.tags.cs451r_groupproject_backend.application.repository.ApplicationRepository;
import com.tags.cs451r_groupproject_backend.student.rest.StudentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ApplicationService {

    private ApplicationRepository applicationRepository;

    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    public Application findById(Long id) {
        Optional<Application> applicationOptional = applicationRepository.findById(id);
        return applicationOptional.orElseThrow(
                () -> new ApplicationNotFoundException(id)
        );
    }

    public Application saveApplication(Application application) {
        return applicationRepository.save(application);
    }

    public Application updateApplication(Application application, Long id) {
        return applicationRepository.findById(id).map(application1 -> {
            application1.setId(application.getId());
            application1.setFirstName(application.getFirstName());
            application1.setLastName(application.getLastName());
            return applicationRepository.save(application1);
        }).orElseThrow(
                () -> new ApplicationNotFoundException(id)
        );
    }

    public void deleteApplication(Long id) {
        Optional<Application> applicationOptional = applicationRepository.findById(id);
        if(applicationOptional.isPresent()) {
            applicationRepository.delete(applicationOptional.get());
        }
        else {
            throw new StudentNotFoundException(id);
        }
    }
}
