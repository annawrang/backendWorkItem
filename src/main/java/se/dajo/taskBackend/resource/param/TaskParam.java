package se.dajo.taskBackend.resource.param;

import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import se.dajo.taskBackend.enums.TaskStatus;

import javax.ws.rs.BeanParam;
import javax.ws.rs.QueryParam;

public final class TaskParam {

    @QueryParam("text")
    private String text;

    @QueryParam("status")
    private String status;

    @QueryParam("issue")
    @Nullable
    private boolean issue;

    public String getText() {
        return text;
    }

    public String getStatus() {
        return status;
    }

    public boolean hasIssue() {
        return issue;
    }
}