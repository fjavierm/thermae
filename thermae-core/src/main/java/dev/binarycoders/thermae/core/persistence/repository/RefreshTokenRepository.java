package dev.binarycoders.thermae.core.persistence.repository;

import dev.binarycoders.thermae.core.persistence.model.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

    Optional<RefreshTokenEntity> findByToken(String token);

    void deleteByToken(String token);
}
