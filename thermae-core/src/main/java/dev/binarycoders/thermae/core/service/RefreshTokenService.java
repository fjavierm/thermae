package dev.binarycoders.thermae.core.service;

import dev.binarycoders.thermae.api.model.RefreshToken;

public interface RefreshTokenService {

    RefreshToken generateRefreshToken();

    void validateRefreshToken(String token);

    void deleteRefreshToken(String token);
}
