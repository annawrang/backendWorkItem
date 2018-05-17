package se.dajo.taskBackend.resource;

import se.dajo.taskBackend.model.data.Task;
import se.dajo.taskBackend.model.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.dajo.taskBackend.resource.param.UserParam;
import se.dajo.taskBackend.service.TaskService;
import se.dajo.taskBackend.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.util.List;

@Component
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class UserResource {

    private final UserService userService;
    private final TaskService taskService;
    @Context
    private UriInfo uriInfo;

    @Autowired
    public UserResource(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @POST
    public Response createUser(User user) {
        user = userService.saveUser(user);

        return Response.ok(user).header("Location", uriInfo.getAbsolutePathBuilder()
                .path(user.getUserNumber().toString())).build();
    }

    @PUT
    public Response updateUser(User user) {
        userService.updateUser(user);
        return Response.ok().build();
    }

    @PUT
    @Path("{userNumber}/tasks/{taskNumber}")
    public Response attachTaskToUser(@PathParam("userNumber") Long userNumber,
                                     @PathParam("taskNumber") Long taskNumber) {
        taskService.updateTask(userNumber, taskNumber);
        return Response.ok().build();
    }

    @GET
    @Path("{userNumber}")
    public Response getUserByUserNumber(@PathParam("userNumber") Long userNumber) {
        User user = userService.getUser(userNumber);
        return Response.ok(user).build();
    }

    @GET
    public Response getUser(@BeanParam UserParam userParam) {
        List<User> user = userService.getUserByFirstNAmeOrSurNameOrUserNumber(userParam);
        // kan vi anropa en metod här som returnerar en Response?
        // typ: return checkIfUsersInList(user);
        return  Response.ok(user).build();
    }

    @GET
    @Path("{userNumber}/tasks")
    public Response getUsersTasks(@PathParam("userNumber") Long userNumber) {
        List<Task> tasks = userService.getUsersTasks(userNumber);
        return Response.ok(tasks).build();
    }
}