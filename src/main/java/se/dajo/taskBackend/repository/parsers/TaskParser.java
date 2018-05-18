package se.dajo.taskBackend.repository.parsers;

import se.dajo.taskBackend.model.data.Task;
import se.dajo.taskBackend.repository.data.TaskDTO;

import java.util.ArrayList;
import java.util.List;

public final class TaskParser {

    public static Task toTask(TaskDTO taskDTO) {
        return new Task(taskDTO.getDescription(), taskDTO.getStatus(), taskDTO.getTaskNumber());
    }

    public static TaskDTO toTaskDTO(Task task) {
        return new TaskDTO(task.getDescription(), task.getStatus(), task.getTaskNumber());
    }

    public static List<Task> toTaskList(Iterable<TaskDTO> taskDTOS) {
        List<Task> modelTasks = new ArrayList<>();
        taskDTOS.forEach(task -> {
            modelTasks.add(TaskParser.toTask(task));
        });
        return modelTasks;
    }

    /**
     * Checks if TaskDTO has instansiated UserDTO or not.
     * @param taskDTO An object of type TaskDTO carrying id and UserDTO.
     * @param task An object of type Task carrying description and status.
     * @return A TaskDTO with or without a UserDTO.
     */
    public static TaskDTO updateTaskDTO(TaskDTO taskDTO, Task task) {

        if (taskDTO.getUser() == null) {
            return new TaskDTO(taskDTO.getId(), task.getDescription(), task.getStatus(), task.getTaskNumber());
        }
        else {
            return new TaskDTO(taskDTO.getId(), task.getDescription(), task.getStatus(), task.getTaskNumber(), taskDTO.getUser());
        }
    }
}
