package dev.binarycoders.thermae.core.service;

import dev.binarycoders.thermae.api.model.Comment;

import java.util.List;

public interface CommentService {

    Comment save(Comment comment);

    List<Comment> getByPost(Long postId);

    List<Comment> getByUser(Long userId);
}
