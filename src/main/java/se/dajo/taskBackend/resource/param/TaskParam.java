package se.dajo.taskBackend.resource.param;

import se.dajo.taskBackend.enums.TaskStatus;

import javax.ws.rs.QueryParam;

public final class TaskParam {

    @QueryParam("text")
    private String text;

    @QueryParam("status")
    private TaskStatus status;

    @QueryParam("issue")
    private boolean issue;

    public String getText() {
        return text;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public boolean hasIssue() {
        return issue;
    }
}
