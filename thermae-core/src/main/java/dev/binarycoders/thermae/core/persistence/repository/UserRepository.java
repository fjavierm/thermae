package dev.binarycoders.thermae.core.persistence.repository;

import dev.binarycoders.thermae.core.persistence.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
