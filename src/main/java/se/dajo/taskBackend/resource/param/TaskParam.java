package se.dajo.taskBackend.resource.param;

import org.springframework.lang.Nullable;
import javax.ws.rs.QueryParam;

public final class TaskParam {

    @QueryParam("text")
    private String text;

    @QueryParam("status")
    private String status;

    @QueryParam("issue")
    @Nullable
    private Boolean issue;

    public String getText() {
        return text;
    }

    public String getStatus() {
        return status;
    }

    public Boolean hasIssue() {
        return issue;
    }
}
