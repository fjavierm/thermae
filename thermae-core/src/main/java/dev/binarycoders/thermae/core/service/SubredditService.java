package dev.binarycoders.thermae.core.service;

import dev.binarycoders.thermae.api.model.Subreddit;

import java.util.List;
import java.util.Optional;

public interface SubredditService {
    Subreddit save(Subreddit subreddit);

    List<Subreddit> getAll();

    Optional<Subreddit> getById(Long id);
}
