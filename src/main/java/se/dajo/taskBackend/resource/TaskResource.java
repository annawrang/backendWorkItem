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

import java.util.List;

import static javax.ws.rs.core.Response.*;
import static javax.ws.rs.core.Response.Status.*;

@Component
@Path("tasks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {

    private final TaskService taskService;
    @Context
    private UriInfo uriInfo;

    @Autowired
    public TaskResource(TaskService service) {
        this.taskService = service;
    }

    @POST
    public Response createTask(Task task){
        task = taskService.saveTask(task);

        return ok(task).header("Location", uriInfo.getAbsolutePathBuilder()
                .path(task.getTaskNumber().toString())).build();
    }

    // BLIR KOMPILERINGSFEL - den här metoden måste flyttas in i @GET som ligger nedan
//    @GET
//    public Response getTaskByStatus(@QueryParam("id") Long id){
//        return null;
//    }

    @PUT
    @Path("{taskNumber}")
    public Response updateTask(@PathParam("taskNumber")Long taskNumber, Task task){
        Task oldTask = taskService.getTask(taskNumber);
        return status(NO_CONTENT).build();
    }

    @GET
    public Response getAllTasks(){
        Iterable<Task> tasks = taskService.getAllTasks();
        return Response.ok(tasks).build();
    }

}
