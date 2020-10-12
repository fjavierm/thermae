package dev.binarycoders.thermae.core.persistence.repository;

import dev.binarycoders.thermae.core.persistence.model.SubredditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubredditRepository extends JpaRepository<SubredditEntity, Long> {
}
