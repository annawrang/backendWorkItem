package se.dajo.taskBackend.resource;

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
import java.util.Optional;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;

@Component
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Autowired
    private UserService service;
    @Context
    private UriInfo uriInfo;

    @POST
    public Response createUser(User user){
        user = service.saveUser(user);

        return Response.ok(user).header("Location", uriInfo.getAbsolutePathBuilder()
                                        .path(user.getUserNumber().toString())).build();
    }

    @PUT
    @Path("{userNumber}")
    public Response updateUser(@PathParam("userNumber")Long userNumber, User user){
        return null;
    }

    @PUT
    @Path("{userNumber}/deactivate")
    public Response deactivateUser(@PathParam("userNumber") Long userNumber){
        return null;
    }

    @GET
    public Response getUser(@QueryParam("firstName") @DefaultValue("0") String firstName,
                            @QueryParam("surName") @DefaultValue("0") String surName,
                            @QueryParam("userNumber") @DefaultValue("0") Long userNumber){


        List<User> user = service.getUserByFirstNAmeOrSurNameOrUserNumber(firstName, surName, userNumber.toString());

        user.forEach(u -> System.out.println(u.getFirstName()));

        if (user.size() == 0) {
            return Response.status(NO_CONTENT).entity("No user matching the request.").build();
        } else {
            return Response.ok(user).build();
        }
    }

}