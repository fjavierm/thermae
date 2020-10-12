package dev.binarycoders.thermae.core.persistence.model;

public enum VoteType {
    POSITIVE(1),
    NEGATIVE(-1);

    private int value;

    VoteType(int value) {
        this.value = value;
    }
}
