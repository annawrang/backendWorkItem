package se.dajo.taskBackend.service;

import se.dajo.taskBackend.model.data.Issue;
import se.dajo.taskBackend.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.dajo.taskBackend.repository.TaskRepository;
import se.dajo.taskBackend.repository.data.IssueDTO;
import se.dajo.taskBackend.repository.data.TaskDTO;
import se.dajo.taskBackend.repository.parsers.IssueParser;


@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private TaskRepository taskRepository;

    public IssueDTO saveIssue(Issue issue, Long taskNumber) {
        TaskDTO taskDTO = taskRepository.findByTaskNumber(taskNumber);
        IssueDTO issueDTO = IssueParser.parseIssueToIssueDTO(issue, taskDTO);
        return issueRepository.save(issueDTO);
    }

}
