package dev.binarycoders.thermae.core.mapper;

import dev.binarycoders.thermae.api.model.RefreshToken;
import dev.binarycoders.thermae.core.persistence.model.RefreshTokenEntity;

public class RefreshTokenMapper implements Mapper<RefreshToken, RefreshTokenEntity> {

    private static final RefreshTokenMapper INSTANCE = new RefreshTokenMapper();

    public static RefreshTokenMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public RefreshToken toApi(final RefreshTokenEntity entity) {
        if (entity == null) {
            return null;
        }

        return RefreshToken.builder()
            .token(entity.getToken())
            .build();
    }

    @Override
    public RefreshTokenEntity toEntity(final RefreshToken refreshToken) {
        if (refreshToken == null) {
            return null;
        }

        return RefreshTokenEntity.builder()
            .token(refreshToken.getToken())
            .build();
    }
}
