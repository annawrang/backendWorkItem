package se.dajo.taskBackend.service;

import se.dajo.taskBackend.model.data.Task;
import se.dajo.taskBackend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.dajo.taskBackend.repository.data.TaskDTO;
import se.dajo.taskBackend.repository.parsers.TaskParser;
import se.dajo.taskBackend.service.exception.InvalidTaskNumberException;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    private AtomicLong taskNumbers;

    public Task saveTask(Task task) {

        this.taskNumbers = new AtomicLong(this.taskRepository.getHighestTaskNumber().orElse(1000000000L));
        task = task.setTaskNumber(taskNumbers.incrementAndGet());

        TaskDTO taskDTO = taskRepository.save(TaskParser.parseTaskToTaskDTO(task));
        return TaskParser.parseTaskDTOToTask(taskDTO);
    }

    public void updateTask(Task task){
        TaskDTO oldTaskDTO = taskRepository.findByTaskNumber(task.getTaskNumber());
        if(oldTaskDTO == null){
            throw new InvalidTaskNumberException("No user found");
        }
        oldTaskDTO = TaskParser.prepareForUpdateTaskDTO(oldTaskDTO, task);
        taskRepository.save(oldTaskDTO);
    }

    public Task getTask(Long taskNumber) {
        TaskDTO taskDTO = taskRepository.findByTaskNumber(taskNumber);
        if(taskDTO == null){
            throw new InvalidTaskNumberException("Task not found");
        }
        return TaskParser.parseTaskDTOToTask(taskDTO);
    }

    public Iterable<Task> getAllTasks() {
        Iterable<TaskDTO> taskDTOS = taskRepository.findAll();
        return TaskParser.parseTaskDTOListToTaskList(taskDTOS);
    }
}
