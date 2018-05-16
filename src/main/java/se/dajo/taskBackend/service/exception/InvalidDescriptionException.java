package se.dajo.taskBackend.service.exception;

public class InvalidDescriptionException extends GeneralException {
    public InvalidDescriptionException(String text) {

        super("No task description containing: " + text);
    }
}
