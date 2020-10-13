package dev.binarycoders.thermae.core.service;

import dev.binarycoders.thermae.api.model.AuthenticationResponse;

public interface AuthService {
    void signup(String username, String email, String password);

    void verifyAccount(String token);

    AuthenticationResponse login(String username, String password);
}
