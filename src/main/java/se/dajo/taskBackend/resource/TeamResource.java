package se.dajo.taskBackend.resource;

import org.springframework.beans.factory.annotation.Autowired;
import se.dajo.taskBackend.model.data.Team;
import org.springframework.stereotype.Component;
import se.dajo.taskBackend.model.data.User;
import se.dajo.taskBackend.service.TeamService;
import se.dajo.taskBackend.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Component
@Path("teams")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeamResource {

    @Autowired
    private TeamService service;
    @Context
    private UriInfo uriInfo;

    @POST
    public Response createTeam(Team team){
        team = service.saveTeam(team);

        return Response.ok(team).header("Location", uriInfo.getAbsolutePathBuilder().path(team.getTeamName())).build();
    }

    @GET
    @Path("/{teamName}")
    public Response displayTeam (@PathParam("teamName") String teamName){
        Team team = service.getTeam(teamName);
        return Response.ok(team).header("Location", uriInfo.getAbsolutePathBuilder().path(team.getTeamName())).build();
    }

    @PUT
    @Path("/{teamName}/users/{userNumber}")
    public Response addTeamUser(@PathParam("teamName") String teamName,
                                @PathParam("userNumber") Long userNumber){

        User user = service.updateUser(teamName, userNumber);

        ///teams/{teamName}/users/{userNumber}
        return Response.ok(user).header("Location", uriInfo.getAbsolutePathBuilder()).build();
    }
}