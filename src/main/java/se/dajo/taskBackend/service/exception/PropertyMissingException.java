package se.dajo.taskBackend.service.exception;

public final class PropertyMissingException extends RuntimeException {
    public PropertyMissingException(String message) {
        super(message);
    }
}
