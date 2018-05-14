package se.dajo.taskBackend.service;

import se.dajo.taskBackend.model.data.Task;
import se.dajo.taskBackend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.dajo.taskBackend.repository.data.TaskDTO;
import se.dajo.taskBackend.repository.parsers.TaskParser;
import se.dajo.taskBackend.service.exception.InvalidTaskNumberException;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task saveTask(Task task) {
        TaskDTO taskDTO = taskRepository.save(TaskParser.parseTaskToTaskDTO(task));
        return TaskParser.parseTaskDTOToTask(taskDTO);
    }

    public void updateTask(Task task){
        TaskDTO oldTaskDTO = taskRepository.findByTaskNumber(task.getTaskNumber());
        if(oldTaskDTO == null){
            throw new InvalidTaskNumberException("No user found");
        }
        oldTaskDTO = oldTaskDTO.updateTaskDTO(task);
        taskRepository.save(oldTaskDTO);
    }
}
