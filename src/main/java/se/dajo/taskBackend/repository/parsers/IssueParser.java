package se.dajo.taskBackend.repository.parsers;

import se.dajo.taskBackend.model.data.Issue;
import se.dajo.taskBackend.repository.data.IssueDTO;
import se.dajo.taskBackend.repository.data.TaskDTO;

public class IssueParser {
    public static IssueDTO parseIssueToIssueDTO(Issue issue, TaskDTO taskDTO) {
        return new IssueDTO (issue.getDescription(), taskDTO);
    }
}
