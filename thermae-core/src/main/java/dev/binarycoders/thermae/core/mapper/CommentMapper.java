package dev.binarycoders.thermae.core.mapper;

import dev.binarycoders.thermae.api.model.Comment;
import dev.binarycoders.thermae.core.persistence.model.CommentEntity;

public class CommentMapper implements Mapper<Comment, CommentEntity> {

    private static final CommentMapper INSTANCE = new CommentMapper();

    public static CommentMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Comment toApi(final CommentEntity entity) {
        return Comment.builder()
            .id(entity.getId())
            .text(entity.getText())
            .created(entity.getCreated())
            .postId(entity.getPost() != null ? entity.getPost().getId() : null)
            .userId(entity.getUser() != null ? entity.getUser().getId() : null)
            .build();
    }

    @Override
    public CommentEntity toEntity(final Comment comment) {
        return CommentEntity.builder()
            .id(comment.getId())
            .text(comment.getText())
            .created(comment.getCreated())
            .build();
    }
}
