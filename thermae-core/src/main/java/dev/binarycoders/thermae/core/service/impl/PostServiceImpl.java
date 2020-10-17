package dev.binarycoders.thermae.core.service.impl;

import dev.binarycoders.thermae.api.model.Post;
import dev.binarycoders.thermae.core.exception.ThermaeException;
import dev.binarycoders.thermae.core.mapper.PostMapper;
import dev.binarycoders.thermae.core.persistence.repository.PostRepository;
import dev.binarycoders.thermae.core.persistence.repository.SubredditRepository;
import dev.binarycoders.thermae.core.service.AuthService;
import dev.binarycoders.thermae.core.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private static final PostMapper POST_MAPPER = PostMapper.getInstance();

    private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;
    private final AuthService authService;

    @Override
    @Transactional
    public Post save(final Post post) {
        final var postEntity = POST_MAPPER.toEntity(post);
        final var user = authService.getCurrentUser();
        final var subreddit = subredditRepository.findById(post.getSubredditId())
            .orElseThrow(() -> new ThermaeException("Subreddit not found to create a post"));

        postEntity.setUser(user);
        postEntity.setSubreddit(subreddit);
        postEntity.setCreated(Instant.now());
        postEntity.setVoteCount(0L);

        final var saved = postRepository.save(postEntity);

        post.setId(saved.getId());

        return post;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> getAll() {
        return postRepository.findAll().stream()
            .map(POST_MAPPER::toApi)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Post> getById(final Long id) {
        final var entity = postRepository.findById(id);

        return entity.map(POST_MAPPER::toApi);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> getBySubreddit(final Long subredditId) {
        return postRepository.findBySubredditId(subredditId).stream()
            .map(POST_MAPPER::toApi)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> getByUser(final Long userId) {
        return postRepository.findByUserId(userId).stream()
            .map(POST_MAPPER::toApi)
            .collect(Collectors.toList());
    }
}
