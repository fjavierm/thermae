package dev.binarycoders.thermae.core.persistence.repository;

import dev.binarycoders.thermae.core.persistence.model.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationTokenEntity, Long> {
}
