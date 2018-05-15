package se.dajo.taskBackend.resource;

import se.dajo.taskBackend.enums.Status;
import se.dajo.taskBackend.model.data.Task;
import se.dajo.taskBackend.model.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.dajo.taskBackend.resource.param.UserParam;
import se.dajo.taskBackend.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static java.util.Collections.singletonMap;

@Component
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserService service;
    @Context
    private UriInfo uriInfo;

    @Autowired
    public UserResource(UserService service){
        this.service = service;
    }

    @POST
    public Response createUser(User user) {
        user = service.saveUser(user);

        return Response.ok(user).header("Location", uriInfo.getAbsolutePathBuilder()
                .path(user.getUserNumber().toString())).build();
    }

    @PUT
    public Response updateUser(User user) {
            service.updateUser(user);
            return Response.ok().build();
    }

    @GET
    @Path("{userNumber}")
    public Response getUserByUserNumber(@PathParam("userNumber") Long userNumber) {
        User user = service.getUser(userNumber);
            return Response.ok(user).build();
    }

    @GET
    public Response getUser(@BeanParam UserParam userParam) {
        List<User> user = service.getUserByFirstNAmeOrSurNameOrUserNumber(userParam);
        // kan vi anropa en metod här som returnerar en Response?
        // typ: return checkIfUsersInList(user);
        if (user.size() == 0) {
            return Response.status(NO_CONTENT).build();
        } else {
            return Response.ok(user).build();
        }
    }

    @GET
    @Path("{userNumber}/tasks")
    public Response getUsersTasks(@PathParam("userNumber") Long userNumber){
        List<Task> tasks = service.getUsersTasks(userNumber);
        return Response.ok(tasks).build();
    }
}