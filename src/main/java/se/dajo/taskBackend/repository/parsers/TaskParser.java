package se.dajo.taskBackend.repository.parsers;

import se.dajo.taskBackend.model.data.Task;
import se.dajo.taskBackend.repository.data.TaskDTO;

import java.util.ArrayList;
import java.util.List;

public final class TaskParser {

    public static Task parseTaskDTOToTask(TaskDTO taskDTO){
        return new Task(taskDTO.getDescription(), taskDTO.getStatus(), taskDTO.getTaskNumber());
    }

    public static TaskDTO parseTaskToTaskDTO(Task task) {
        return new TaskDTO(task.getDescription(), task.getStatus(), task.getTaskNumber());
    }

    public static List<Task> parseTaskDTOListToTaskList(Iterable<TaskDTO> taskDTOS){
        List<Task> modelTasks = new ArrayList<>();
        taskDTOS.forEach(task -> {
            modelTasks.add(TaskParser.parseTaskDTOToTask(task));
        });
        return modelTasks;
    }

    public static TaskDTO prepareForUpdateTaskDTO(TaskDTO taskDTO, Task task){
        return new TaskDTO(taskDTO.getId(), task.getDescription(), task.getStatus(), task.getTaskNumber());
    }
}
