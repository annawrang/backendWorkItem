package se.dajo.taskBackend.service.exception;

public class InactiveUserException extends RuntimeException {
    public InactiveUserException(String message) { super(message);
    }
}
