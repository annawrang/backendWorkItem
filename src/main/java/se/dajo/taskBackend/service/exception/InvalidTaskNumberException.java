package se.dajo.taskBackend.service.exception;

public final class InvalidTaskNumberException extends RuntimeException {
    public InvalidTaskNumberException(String message) {
        super(message);
    }
}
