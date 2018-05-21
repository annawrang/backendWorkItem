package se.dajo.taskBackend.resource;

import org.springframework.beans.factory.annotation.Autowired;
import se.dajo.taskBackend.model.data.Task;
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
import java.util.List;

@Component
@Path("teams")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public final class TeamResource {

    private final TeamService teamService;
    private final UserService userService;
    @Context
    private UriInfo uriInfo;

    @Autowired
    public TeamResource(TeamService service, UserService userService) {
        this.teamService = service;
        this.userService = userService;
    }

    @POST
    public Response createTeam(Team team) {
        team = teamService.saveTeam(team);
        return Response.ok(team).header("Location",
                uriInfo.getAbsolutePathBuilder().path(team.getTeamName())).build();
    }

    @GET
    @Path("/{teamName}")
    public Response displayTeam(@PathParam("teamName") String teamName) {
        Team team = teamService.getTeam(teamName);
        return Response.ok(team).header("Location",
                uriInfo.getAbsolutePathBuilder().path(team.getTeamName())).build();
    }

    @GET
    public Response getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        return Response.ok(teams).build();
    }

    @GET
    @Path("{teamName}/users")
    public Response getUsersInTeam(@PathParam("teamName") String teamName) {
        List<User> users = teamService.getUsersInTeam(teamName);
        return Response.ok(users).build();
    }

    @GET
    @Path("{teamName}/tasks")
    public Response getTasksInTeam(@PathParam("teamName") String teamName) {
        List<Task> teams = teamService.getTasksInTeam(teamName);
        return Response.ok(teams).build();
    }

    @PUT
    @Path("/{teamName}/users/{userNumber}")
    public Response addTeamUser(@PathParam("teamName") String teamName,
                                @PathParam("userNumber") Long userNumber) {
        User user = userService.updateUser(teamName, userNumber);
        return Response.ok(user).header("Location", uriInfo.getAbsolutePathBuilder()).build();
    }

    @PUT
    @Path("/{teamName}")
    public Response updateTeam(@PathParam("teamName") String teamName, Team team) {
        teamService.updateTeam(teamName, team);
        return Response.ok().build();
    }
}