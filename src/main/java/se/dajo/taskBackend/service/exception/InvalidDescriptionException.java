package se.dajo.taskBackend.service.exception;

public class InvalidDescriptionException extends RuntimeException {
    public InvalidDescriptionException(String message) {
        super(message);
    }
}
