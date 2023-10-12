package com.tags.cs451r_groupproject_backend.administrator.service;

import com.tags.cs451r_groupproject_backend.administrator.model.Administrator;
import com.tags.cs451r_groupproject_backend.administrator.repository.AdministratorRepository;
import com.tags.cs451r_groupproject_backend.administrator.rest.AdministratorNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdministratorService {

    private AdministratorRepository administratorRepository;

    public List<Administrator> findAll() {
        return administratorRepository.findAll();
    }

    public Administrator findById(Long id) {
        Optional<Administrator> administratorOptional = administratorRepository.findById(id);
        return administratorOptional.orElseThrow(
                () -> new AdministratorNotFoundException(id)
        );
    }

    public Administrator saveAdministrator(Administrator administrator) {
        return administratorRepository.save(administrator);
    }

    public Administrator updateAdministrator(Administrator administrator, Long id) {
        return administratorRepository.findById(id).map(adm -> {
            adm.setId(administrator.getId());
            adm.setFirstName(administrator.getFirstName());
            adm.setLastName(administrator.getLastName());
            return administratorRepository.save(adm);
        }).orElseThrow(
                () -> new AdministratorNotFoundException(id)
        );
    }

    public void deleteAdministrator(Long id) {
        Optional<Administrator> administratorOptional = administratorRepository.findById(id);
        if(administratorOptional.isPresent()) {
            administratorRepository.delete(administratorOptional.get());
        }
        else {
            throw new AdministratorNotFoundException(id);
        }
    }
}
