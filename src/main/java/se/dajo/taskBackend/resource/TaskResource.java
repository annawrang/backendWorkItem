package se.dajo.taskBackend.resource;

import org.springframework.beans.factory.annotation.Autowired;
import se.dajo.taskBackend.enums.TaskStatus;
import se.dajo.taskBackend.model.data.Issue;
import se.dajo.taskBackend.model.data.Task;
import org.springframework.stereotype.Component;
import se.dajo.taskBackend.service.IssueService;
import se.dajo.taskBackend.resource.param.TaskParam;
import se.dajo.taskBackend.service.TaskService;
import se.dajo.taskBackend.service.exception.InvalidStatusException;

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
    private final IssueService issueService;

    @Context
    private UriInfo uriInfo;

    @Autowired
    public TaskResource(TaskService service, IssueService issueService) {
        this.taskService = service;
        this.issueService = issueService;
    }

    @POST
    public Response createTask(Task task){
        task = taskService.saveTask(task);

        return ok(task).header("Location", uriInfo.getAbsolutePathBuilder()
                .path(task.getTaskNumber().toString())).build();
    }

    @PUT
    @Path("{taskNumber}")
    public Response updateTask(@PathParam("taskNumber")Long taskNumber, Task task){
        Task oldTask = taskService.getTask(taskNumber);
        return status(NO_CONTENT).build();
    }

    // Den här tar hand om Status & om den innehåller en viss text & ett visst issue
    @GET
    public Response getTasks(@BeanParam TaskParam taskParam){
        if (taskParam.getText() != null) {
            List<Task> tasks = taskService.getTaskByDescription(taskParam.getText());
            return Response.ok(tasks).build();
        }
        else if (!taskParam.getStatus().equals(null)) {
            TaskStatus status;
            switch (taskParam.getStatus()) {
                case "unstarted":
                    status = TaskStatus.UNSTARTED;
                    break;
                case "started":
                    status = TaskStatus.STARTED;
                    break;
                case "done":
                    status = TaskStatus.DONE;
                    break;
                case "annulled":
                    status = TaskStatus.ANNULLED;
                    break;
                default:
                    return status(BAD_REQUEST).build();
            }
            List<Task> tasks = taskService.getTaskByStatus(status);
            return Response.ok(tasks).build();
        }
        else if (taskParam.hasIssue() == true) {
                List<Task> tasks = taskService.getTasksWithIssue();
                return Response.ok(tasks).build();
        }
        return status(BAD_REQUEST).build();
    }

    @POST
    @Path("{taskNumber}/issues")
    public Response createIssue(@PathParam("taskNumber") Long taskNumber, Issue issue) {
        Task task = taskService.getTask(taskNumber);
        if (task.getStatus() != TaskStatus.DONE) {
            throw new InvalidStatusException("Task does not have status done, cannot add an issue");
        }
        issueService.saveIssue(issue, taskNumber);
        task.setStatus(TaskStatus.UNSTARTED);
        taskService.updateTask(task);
        return ok().header("Location", uriInfo.getAbsolutePathBuilder().path(issue.getDescription())).build();
    }

}
