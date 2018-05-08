package se.dajo.taskBackend.resource;

import se.dajo.taskBackend.model.data.Issue;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("issues")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IssueResource {

    @POST
    public Response createIssue(Issue issue){
        return null;
    }
}
