package com.tags.cs451r_groupproject_backend.user;

import com.tags.cs451r_groupproject_backend.application.dto.ApplicationDTO;
import com.tags.cs451r_groupproject_backend.application.model.Application;
import com.tags.cs451r_groupproject_backend.general.Copier;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO implements Copier<User> {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private List<ApplicationDTO> applications = new ArrayList<>();

    public UserDTO(User user) {
        copyFrom(user);
        for(Application application : user.getApplications()) {
            ApplicationDTO applicationDTO = new ApplicationDTO();
            applicationDTO.copyFrom(application);
            applications.add(applicationDTO);
        }
    }

    @Override
    public void copyFrom(User entity) {
        id = entity.getId();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        email = entity.getUsername();
        role = entity.getRole();
    }
}
