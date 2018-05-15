package se.dajo.taskBackend.service.exception;

public class InvalidSpaceInTeamException extends RuntimeException {
    public InvalidSpaceInTeamException(String message) {
        super(message);
    }
}