package dev.binarycoders.thermae.core.exception;

public class ThermaeException extends RuntimeException {

    public ThermaeException(final String message) {
        super(message);
    }

    public ThermaeException(final String message, final Throwable cause) {
        super(message, cause);
    }
}

