package se.dajo.taskBackend.service.exception;

public final class InvalidStatusException extends RuntimeException {
    public InvalidStatusException(String message) {
        super(message);
    }
}
