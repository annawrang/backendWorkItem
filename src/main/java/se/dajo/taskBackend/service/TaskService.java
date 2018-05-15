package se.dajo.taskBackend.service;

import se.dajo.taskBackend.enums.Status;
import org.glassfish.jersey.internal.guava.Lists;
import se.dajo.taskBackend.enums.TaskStatus;
import se.dajo.taskBackend.model.data.Issue;
import se.dajo.taskBackend.model.data.Task;
import se.dajo.taskBackend.model.data.User;
import se.dajo.taskBackend.repository.IssueRepository;
import se.dajo.taskBackend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.dajo.taskBackend.repository.UserRepository;
import se.dajo.taskBackend.repository.data.IssueDTO;
import se.dajo.taskBackend.repository.data.TaskDTO;
import se.dajo.taskBackend.repository.parsers.TaskParser;
import se.dajo.taskBackend.service.exception.InactiveUserException;
import se.dajo.taskBackend.service.exception.InvalidDescriptionException;
import se.dajo.taskBackend.service.exception.InvalidStatusException;
import se.dajo.taskBackend.service.exception.InvalidTaskNumberException;
import se.dajo.taskBackend.service.exception.OverworkedUserException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IssueRepository issueRepository;
    private AtomicLong taskNumbers;
    @Autowired
    private UserService userService;

    private final int maximumAmountOfTasksForUser = 5;

    public Task saveTask(Task task) {

        this.taskNumbers = new AtomicLong(this.taskRepository.getHighestTaskNumber().orElse(1000000000L));
        task = task.setTaskNumber(taskNumbers.incrementAndGet());

        TaskDTO taskDTO = taskRepository.save(TaskParser.parseTaskToTaskDTO(task));
        return TaskParser.parseTaskDTOToTask(taskDTO);
    }

    public void updateTask(Long userNumber, Long taskNumber) {

        validateRoomForTask(userNumber);
        validateUserActiveStatus(userNumber);
        getTask(taskNumber);

        TaskDTO taskDTO = taskRepository.findByTaskNumber(taskNumber);
        if(taskDTO == null){
            throw new InvalidTaskNumberException("No user found");
        }
        taskDTO.setUser(userRepository.findUserDTOByUserNumber(userNumber));
        taskRepository.save(taskDTO);
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

    public List<Task> getTaskByDescription(String text) {
        List<TaskDTO> taskDTOs = taskRepository.findByDescriptionContaining(text);
        if (taskDTOs.isEmpty()) {
            throw new InvalidDescriptionException("No task description containing " + text);
        }
        return TaskParser.parseTaskDTOListToTaskList(taskDTOs);
    }

    public List<Task> getTaskByStatus(TaskStatus status) {
        List<TaskDTO> taskDTOs = taskRepository.findByStatus(status);
        if (taskDTOs.isEmpty()) {
            throw new InvalidStatusException("No task with status " + status.toString() + " found");
        }
        return TaskParser.parseTaskDTOListToTaskList(taskDTOs);
    }

    public List<Task> getTasksWithIssue() {
        List<IssueDTO> issueDTOs = Lists.newArrayList(issueRepository.findAll());
        List<Task> tasks = new ArrayList<>();
        for (IssueDTO issueDTO : issueDTOs) {
            tasks.add(TaskParser.parseTaskDTOToTask(issueDTO.getTaskDTO()));
        }
        return tasks;
    }

    public void validateRoomForTask(Long userNumber) {

        if (taskRepository.countTaskDTOByUser(userRepository.findUserDTOByUserNumber(userNumber)) >= maximumAmountOfTasksForUser) {
            throw new OverworkedUserException("Poor user has too little time");
        }
    }
    public void validateUserActiveStatus(Long userNumber) {

        User user = userService.getUser(userNumber);
        if (user.getStatus().equals(Status.INACTIVE)) {
            throw new InactiveUserException("The user is not active");
        }
    }
}
