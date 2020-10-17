package dev.binarycoders.thermae.core.service.impl;

import dev.binarycoders.thermae.api.model.RefreshToken;
import dev.binarycoders.thermae.core.exception.ThermaeException;
import dev.binarycoders.thermae.core.mapper.RefreshTokenMapper;
import dev.binarycoders.thermae.core.persistence.model.RefreshTokenEntity;
import dev.binarycoders.thermae.core.persistence.repository.RefreshTokenRepository;
import dev.binarycoders.thermae.core.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private static final RefreshTokenMapper REFRESH_TOKEN_MAPPER = RefreshTokenMapper.getInstance();

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    @Override
    public RefreshToken generateRefreshToken() {
        final RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();

        refreshTokenEntity.setToken(UUID.randomUUID().toString());
        refreshTokenEntity.setCreated(Instant.now());

        final var saved = refreshTokenRepository.save(refreshTokenEntity);

        return REFRESH_TOKEN_MAPPER.toApi(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public void validateRefreshToken(String token) {
        refreshTokenRepository.findByToken(token)
            .orElseThrow(() -> new ThermaeException("Invalid refresh Token"));
    }

    @Transactional
    @Override
    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}
