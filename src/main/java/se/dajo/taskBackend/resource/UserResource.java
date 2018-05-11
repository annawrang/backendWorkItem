package se.dajo.taskBackend.resource;

import se.dajo.taskBackend.enums.Status;
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

    @Autowired
    private UserService service;
    @Context
    private UriInfo uriInfo;

    private static final AtomicLong ids = new AtomicLong(1000000000);

    @POST
    public Response createUser(User user) {
        user = user.setUserNumber(ids.incrementAndGet());
        user = service.saveUser(user);

        return Response.ok(user).header("Location", uriInfo.getAbsolutePathBuilder()
                .path(user.getUserNumber().toString())).build();
    }

    @PUT
    @Path("{userNumber}")
    public Response updateUser(@PathParam("userNumber") Long userNumber, User user) {
        return null;
    }

    @PUT
    @Path("{userNumber}/deactivate")
    public Response deactivateUser(@PathParam("userNumber") Long userNumber) {

        User user = service.getUser(userNumber);
        if (user == null) {
            return Response.status(NOT_FOUND).build();
        } else {
            service.deactivateUser(user);
            return Response.ok().build();
        }
    }

    @GET
    @Path("{userNumber}")
    public Response getUserByUserNumber(@PathParam("userNumber") Long userNumber) {

        User user = service.getUser(userNumber);
        if (user == null) {
            return Response.status(NOT_FOUND).build();
        } else {
            return Response.ok(user).build();
        }
    }

    @GET
    public Response getUser(@BeanParam UserParam userParam) {
        List<User> user = service.getUserByFirstNAmeOrSurNameOrUserNumber(userParam);
        if (user.size() == 0) {
            return Response.status(NO_CONTENT).build();
        } else {
            return Response.ok(user).build();
        }


    }

}