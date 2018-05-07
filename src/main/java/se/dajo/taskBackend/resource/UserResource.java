package main.java.se.dajo.taskBackend.resource;

import main.java.se.dajo.taskBackend.model.data.User;
import main.java.se.dajo.taskBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

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
    @Path("{userNumber}")
    public Response getUser(@PathParam("userNumber") Long userNumber){
        return null;
    }

    @GET
    public Response getUserByFirstNameAndSurName(@QueryParam("firstName") String firstName,
                                                 @QueryParam("surName") String surName){
        return null;
    }

}
