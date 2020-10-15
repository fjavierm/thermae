package dev.binarycoders.thermae.core.service.impl;

import dev.binarycoders.thermae.api.model.Subreddit;
import dev.binarycoders.thermae.core.exception.ThermaeException;
import dev.binarycoders.thermae.core.mapper.SubredditMapper;
import dev.binarycoders.thermae.core.persistence.repository.SubredditRepository;
import dev.binarycoders.thermae.core.persistence.repository.UserRepository;
import dev.binarycoders.thermae.core.service.SubredditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditServiceImpl implements SubredditService {

    private static final SubredditMapper SUBREDDIT_MAPPER = SubredditMapper.getInstance();

    private final SubredditRepository subredditRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Subreddit save(final Subreddit subreddit) {
        final var username = SecurityContextHolder.getContext().getAuthentication().getName();
        final var entity = SUBREDDIT_MAPPER.toEntity(subreddit);
        final var user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new ThermaeException("User not allowed to create subreddits");
        }

        entity.setCreated(Instant.now());
        entity.setUser(user.get());

        final var saved = subredditRepository.save(entity);

        subreddit.setId(saved.getId());

        return subreddit;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subreddit> getAll() {
        return subredditRepository.findAll().stream()
            .map(SUBREDDIT_MAPPER::toApi)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Subreddit> getById(final Long id) {
        final var entity = subredditRepository.findById(id);

        return entity.map(SUBREDDIT_MAPPER::toApi);
    }
}
