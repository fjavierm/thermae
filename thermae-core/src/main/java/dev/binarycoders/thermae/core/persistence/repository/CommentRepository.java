package dev.binarycoders.thermae.core.persistence.repository;

import dev.binarycoders.thermae.core.persistence.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
