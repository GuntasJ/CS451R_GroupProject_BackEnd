package com.tags.cs451r_groupproject_backend.authentication;

import com.tags.cs451r_groupproject_backend.security.JWTService;
import com.tags.cs451r_groupproject_backend.user.Role;
import com.tags.cs451r_groupproject_backend.user.User;
import com.tags.cs451r_groupproject_backend.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JWTService jwtService;
    private AuthenticationManager authenticationManager;

    public TokenResponse registerStudent(RegisterRequest registerRequest) {
        User user = new User(
                registerRequest.firstName(),
                registerRequest.lastName(),
                registerRequest.username(),
                passwordEncoder.encode(registerRequest.password()),
                Role.STUDENT
        );
        userRepository.save(user);
        String jwtToken = jwtService.generateJwtToken(user, Collections.emptyMap());
        return new TokenResponse(jwtToken, Role.STUDENT.name());
    }

    public TokenResponse authenticateAccount(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.username(), authenticationRequest.password()
                )
        );
        User user = userRepository.findByUsername(authenticationRequest.username()).orElseThrow(
                () -> new RuntimeException("No user for username " + authenticationRequest.username())
        );

        String jwtToken = jwtService.generateJwtToken(user, Collections.emptyMap());
        return new TokenResponse(jwtToken, user.getRole().name());
    }

}
