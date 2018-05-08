package se.dajo.taskBackend.service.exception;

public final class InvalidUserNumberException extends RuntimeException {
    public InvalidUserNumberException(String message) {
        super(message);
    }
}
