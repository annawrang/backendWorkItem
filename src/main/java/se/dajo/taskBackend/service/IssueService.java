package se.dajo.taskBackend.service;

import se.dajo.taskBackend.enums.TaskStatus;
import se.dajo.taskBackend.model.data.Issue;
import se.dajo.taskBackend.model.data.Task;
import se.dajo.taskBackend.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.dajo.taskBackend.repository.TaskRepository;
import se.dajo.taskBackend.repository.data.IssueDTO;
import se.dajo.taskBackend.repository.data.TaskDTO;
import se.dajo.taskBackend.repository.parsers.IssueParser;
import se.dajo.taskBackend.service.exception.InvalidStatusException;


@Service
public final class IssueService {

    private final IssueRepository issueRepository;
    private final TaskRepository taskRepository;
    private final TaskService taskService;

    @Autowired
    public IssueService(IssueRepository issueRepository, TaskRepository taskRepository, TaskService taskService) {
        this.issueRepository = issueRepository;
        this.taskRepository = taskRepository;
        this.taskService = taskService;
    }

    public Issue saveIssue(Issue issue, Long taskNumber) {

        Task task = taskService.getTask(taskNumber);
        if (task.getStatus() != TaskStatus.DONE) {
            throw new InvalidStatusException();
        }
        TaskDTO taskDTO = taskRepository.findByTaskNumber(taskNumber);
        IssueDTO issueDTO = IssueParser.toIssueDTO(issue, taskDTO);
        task.setStatus(TaskStatus.UNSTARTED);
        taskService.updateTask(task);
        issueRepository.save(issueDTO);
        issue = IssueParser.parseIssueDTOToIssue(issueDTO);
        return issue;
    }

}
