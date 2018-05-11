package se.dajo.taskBackend.resource;

import org.springframework.beans.factory.annotation.Autowired;
import se.dajo.taskBackend.model.data.Task;
import org.springframework.stereotype.Component;
import se.dajo.taskBackend.service.TaskService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Component
@Path("tasks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {

    @Autowired
    private TaskService service;
    @Context
    private UriInfo uriInfo;

    @POST
    public Response createTask(Task task){
        task = service.saveTask();
        return null;
    }
}
