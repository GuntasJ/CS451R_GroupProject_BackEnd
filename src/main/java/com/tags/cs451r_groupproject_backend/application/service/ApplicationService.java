package com.tags.cs451r_groupproject_backend.application.service;

import com.tags.cs451r_groupproject_backend.application.model.Application;
import com.tags.cs451r_groupproject_backend.application.model.ApplicationStatus;
import com.tags.cs451r_groupproject_backend.application.rest.ApplicationNotFoundException;
import com.tags.cs451r_groupproject_backend.application.repository.ApplicationRepository;
import com.tags.cs451r_groupproject_backend.filetransfer.model.File;
import com.tags.cs451r_groupproject_backend.filetransfer.repository.FileRepository;
import com.tags.cs451r_groupproject_backend.filetransfer.rest.FileNotFoundException;
import com.tags.cs451r_groupproject_backend.position.model.PositionId;
import com.tags.cs451r_groupproject_backend.position.respository.PositionRepository;
import com.tags.cs451r_groupproject_backend.user.User;
import com.tags.cs451r_groupproject_backend.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ApplicationService {

    private ApplicationRepository applicationRepository;
    private PositionRepository positionRepository;
    private FileRepository fileRepository;
    private UserRepository userRepository;

    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    public Application findById(Long id) {
        Optional<Application> applicationOptional = applicationRepository.findById(id);
        return applicationOptional.orElseThrow(() -> new ApplicationNotFoundException(id));
    }

    public List<File> findFilesByApplicationId(Long id) {
        return findById(id).getFiles();
    }

    public Application createApplication(Application application, Long fileId, Long studentId) {
        //Check if a valid position exists that matches up with the applications desiredClasses.
        //If so, add it to it.
        //Don't add the position if the application doesn't meet the required standing.
        for(String desiredClass : application.getDesiredClasses()) {
            for(String desiredType : application.getDesiredTypes()) {
                PositionId id = new PositionId(desiredClass, desiredType);
                positionRepository.findById(id).ifPresent(application::addPosition);
            }
        }

        if (fileId != null) {
            Optional<File> fileOptional = fileRepository.findById(fileId);
            fileOptional.ifPresent(file -> file.setOwnerApplicationId(application.getId()));
            application.addFile(fileOptional.orElseThrow(() -> new FileNotFoundException(fileId)));
        }

        User user = userRepository.findById(studentId).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );

        user.addApplication(application);

        return applicationRepository.save(application);
    }

    public Application updateApplication(Application newApplication, Long id) {
        return applicationRepository.findById(id).map(application -> {
            application.copyFrom(newApplication);
            return applicationRepository.save(application);
        }).orElseThrow(() -> new ApplicationNotFoundException(id));
    }

    public void deleteApplication(Long id) {
        Optional<Application> applicationOptional = applicationRepository.findById(id);
        if(applicationOptional.isPresent()) {
            Application application = applicationOptional.get();
            while(application.getFiles().size() != 0) {
                application.getFiles().get(0).setOwnerApplicationId(null);
                application.removeFile(application.getFiles().get(0));
            }
            while(application.getPositions().size() != 0) {
                application.removePosition(application.getPositions().get(0));
            }
            applicationRepository.delete(application);
        }
        else {
            throw new ApplicationNotFoundException(id);
        }
    }

    public Application updateStatus(ApplicationStatus applicationStatus, Long id) {
        return applicationRepository.findById(id).map(application -> {
            application.setApplicationStatus(applicationStatus);
            return applicationRepository.save(application);
        }).orElseThrow(() -> new ApplicationNotFoundException(id));
    }
}
