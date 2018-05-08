package se.dajo.taskBackend.resource;

import se.dajo.taskBackend.model.data.Team;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("teams")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeamResource {

    @POST
    public Response createTeam(Team team){
        return null;
    }
}