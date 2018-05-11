package se.dajo.taskBackend.repository.parsers;

import se.dajo.taskBackend.model.data.Task;
import se.dajo.taskBackend.repository.data.TaskDTO;

import java.util.ArrayList;
import java.util.List;

public final class TaskParser {

    public static Task parseTaskDTOToTask(TaskDTO taskDTO){
        return new Task(taskDTO.getDescription(), taskDTO.getStatus());
    }

    public static TaskDTO parseTaskToTaskDTO(Task task) {
        return new TaskDTO(task.getDescription(), task.getStatus());
    }

    public static List<Task> parseTaskDTOListToTaskList(List<TaskDTO> taskDTOS){
        List<Task> modelTasks = new ArrayList<>();
        taskDTOS.forEach(task -> {
            modelTasks.add(TaskParser.parseTaskDTOToTask(task));
        });
        return modelTasks;
    }
}
