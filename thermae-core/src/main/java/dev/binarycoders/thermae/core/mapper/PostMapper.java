package dev.binarycoders.thermae.core.mapper;

import dev.binarycoders.thermae.api.model.Post;
import dev.binarycoders.thermae.core.persistence.model.PostEntity;

public class PostMapper implements Mapper<Post, PostEntity> {

    private static final PostMapper INSTANCE = new PostMapper();

    public static PostMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Post toApi(final PostEntity entity) {
        if (entity == null) {
            return null;
        }

        return Post.builder()
            .id(entity.getId())
            .name(entity.getName())
            .description(entity.getDescription())
            .created(entity.getCreated())
            .url(entity.getUrl())
            .voteCount(entity.getVoteCount())
            .userId(entity.getUser() != null ? entity.getUser().getId() : null)
            .subredditId(entity.getSubreddit() != null ? entity.getSubreddit().getId() : null)
            .build();
    }

    @Override
    public PostEntity toEntity(final Post post) {
        if (post == null) {
            return null;
        }

        return PostEntity.builder()
            .id(post.getId())
            .name(post.getName())
            .description(post.getDescription())
            .created(post.getCreated())
            .url(post.getUrl())
            .voteCount(post.getVoteCount())
            .build();
    }
}
