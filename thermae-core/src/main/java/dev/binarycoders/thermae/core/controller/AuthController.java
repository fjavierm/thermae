package dev.binarycoders.thermae.core.controller;

import dev.binarycoders.thermae.api.model.AuthenticationResponse;
import dev.binarycoders.thermae.api.model.LoginRequest;
import dev.binarycoders.thermae.api.model.RegisterRequest;
import dev.binarycoders.thermae.core.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/signup")
    public ResponseEntity<String> signup(@RequestBody final RegisterRequest request) {
        // TODO Add validation
        authService.signup(request.getUsername(), request.getEmail(), request.getPassword());

        return ResponseEntity.ok("User registration successful");
    }

    @GetMapping(value = "/account-verification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable final String token) {
        authService.verifyAccount(token);

        return ResponseEntity.ok("Account activated successfully");
    }

    @PostMapping(value = "/login")
    public AuthenticationResponse login(@RequestBody final LoginRequest request) {
        // TODO Add validation
        return authService.login(request.getUsername(), request.getPassword());
    }
}
