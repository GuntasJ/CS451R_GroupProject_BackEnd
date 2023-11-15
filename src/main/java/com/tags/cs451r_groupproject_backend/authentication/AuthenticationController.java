package com.tags.cs451r_groupproject_backend.authentication;

import jdk.jfr.Registered;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<TokenResponse> registerStudent(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.registerStudent(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticateAccount(authenticationRequest));
    }

}
