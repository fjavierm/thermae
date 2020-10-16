package dev.binarycoders.thermae.core.service;

import dev.binarycoders.thermae.api.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Post save(Post post);

    List<Post> getAll();

    Optional<Post> getById(Long id);

    List<Post> getBySubreddit(Long subredditId);

    List<Post> getByUser(Long userId);
}
