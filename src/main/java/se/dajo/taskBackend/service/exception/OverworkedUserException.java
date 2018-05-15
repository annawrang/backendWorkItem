package se.dajo.taskBackend.service.exception;

public class OverworkedUserException extends RuntimeException {
    public OverworkedUserException(String message) { super(message);
    }
}
