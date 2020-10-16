package dev.binarycoders.thermae.core.service;

import dev.binarycoders.thermae.api.model.AuthenticationResponse;
import dev.binarycoders.thermae.core.persistence.model.UserEntity;

public interface AuthService {
    void signup(String username, String email, String password);

    void verifyAccount(String token);

    AuthenticationResponse login(String username, String password);

    UserEntity getCurrentUser();
}
