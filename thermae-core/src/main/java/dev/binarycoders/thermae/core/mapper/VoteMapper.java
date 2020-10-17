package dev.binarycoders.thermae.core.mapper;

import dev.binarycoders.thermae.api.model.Vote;
import dev.binarycoders.thermae.core.persistence.model.VoteEntity;
import dev.binarycoders.thermae.core.persistence.model.VoteType;

public class VoteMapper implements Mapper<Vote, VoteEntity> {

    private static final VoteMapper INSTANCE = new VoteMapper();

    public static VoteMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Vote toApi(final VoteEntity entity) {
        return Vote.builder()
            .direction(entity.getType().getDirection())
            .userId(entity.getUser() != null ? entity.getUser().getId() : null)
            .postId(entity.getPost() != null ? entity.getPost().getId() : null)
            .build();
    }

    @Override
    public VoteEntity toEntity(final Vote vote) {
        return VoteEntity.builder()
            .type(VoteType.lookup(vote.getDirection()))
            .build();
    }
}
