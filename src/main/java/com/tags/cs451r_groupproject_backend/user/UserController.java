package com.tags.cs451r_groupproject_backend.user;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/user/{email}")
    public ResponseEntity<UserDTO> retrieveUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(new UserDTO(userService.findUserByUsername(email)));
    }

}
