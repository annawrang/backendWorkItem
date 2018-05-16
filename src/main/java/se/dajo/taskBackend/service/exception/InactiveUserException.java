package se.dajo.taskBackend.service.exception;

public class InactiveUserException extends GeneralException {
    public InactiveUserException() {

        super("The user is not active");
    }
}
