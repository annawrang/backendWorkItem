package se.dajo.taskBackend.service.exception;

public final class InvalidTaskRequestException extends RuntimeException {
    public InvalidTaskRequestException(String message) {
        super(message);
    }
}
