package dev.binarycoders.thermae.core.persistence.model;

import dev.binarycoders.thermae.core.exception.ThermaeException;

import java.util.Arrays;

public enum VoteType {
    UP(1),
    DOWN(-1);

    private int direction;

    VoteType(int direction) {
        this.direction = direction;
    }

    public static VoteType lookup(final Integer direction) {
        return Arrays.stream(VoteType.values())
            .filter(voteType -> direction == voteType.direction)
            .findFirst()
            .orElseThrow(() -> new ThermaeException(String.format("Direction %d does not exist.", direction)));
    }

    public int getDirection() {
        return direction;
    }
}
