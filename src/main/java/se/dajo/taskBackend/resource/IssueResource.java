package se.dajo.taskBackend.resource;

import org.springframework.beans.factory.annotation.Autowired;
import se.dajo.taskBackend.model.data.Issue;
import org.springframework.stereotype.Component;
import se.dajo.taskBackend.service.IssueService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Component
@Path("issues")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IssueResource {

    private final IssueService service;
    @Context
    private UriInfo uriInfo;

    @Autowired
    public IssueResource(IssueService service) {
        this.service = service;
    }

    @POST
    public Response createIssue(Issue issue){
        return null;
    }
}
