package dev.binarycoders.thermae.core.mapper;

import dev.binarycoders.thermae.api.model.Subreddit;
import dev.binarycoders.thermae.core.persistence.model.SubredditEntity;

public class SubredditMapper implements Mapper<Subreddit, SubredditEntity> {

    private static final SubredditMapper INSTANCE = new SubredditMapper();

    public static SubredditMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Subreddit toApi(final SubredditEntity entity) {
        if (entity == null) {
            return null;
        }

        return Subreddit.builder()
            .id(entity.getId())
            .name(entity.getName())
            .description(entity.getDescription())
            .numberOfPosts(entity.getPosts().size())
            .build();
    }

    @Override
    public SubredditEntity toEntity(final Subreddit subreddit) {
        if (subreddit == null) {
            return null;
        }

        return SubredditEntity.builder()
            .id(subreddit.getId())
            .name(subreddit.getName())
            .description(subreddit.getDescription())
            .build();
    }
}
