package se.dajo.taskBackend.service.exception;

public class InvalidSpaceInTeamException extends GeneralException {
    public InvalidSpaceInTeamException() {

        super("No space in team for user");
    }
}