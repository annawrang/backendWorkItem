package se.dajo.taskBackend.resource;

import org.springframework.beans.factory.annotation.Autowired;
import se.dajo.taskBackend.model.data.Task;
import org.springframework.stereotype.Component;
import se.dajo.taskBackend.service.TaskService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Component
@Path("tasks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {

    private final TaskService service;
    @Context
    private UriInfo uriInfo;

    @Autowired
    public TaskResource(TaskService service) {
        this.service = service;
    }

    @POST
    public Response createTask(Task task){
        task = service.saveTask(task);
        return Response.ok(task).header("Location", uriInfo.getAbsolutePathBuilder()
                .path(task.getDescription().toString())).build();
    }

    @GET
    public Response getTaskByStatus(){
        return;
    }
}
