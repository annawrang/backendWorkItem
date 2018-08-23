package se.dajo.taskBackend;

import org.junit.Test;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.dajo.taskBackend.enums.TaskStatus;
import se.dajo.taskBackend.model.data.Issue;
import se.dajo.taskBackend.model.data.Task;
import se.dajo.taskBackend.repository.IssueRepository;
import se.dajo.taskBackend.repository.TaskRepository;
import se.dajo.taskBackend.repository.data.IssueDTO;
import se.dajo.taskBackend.repository.data.TaskDTO;
import se.dajo.taskBackend.repository.parsers.TaskParser;
import se.dajo.taskBackend.resource.TaskResource;
import se.dajo.taskBackend.service.IssueService;
import se.dajo.taskBackend.service.TaskService;

import static se.dajo.taskBackend.enums.TaskStatus.DONE;
import static se.dajo.taskBackend.enums.TaskStatus.UNSTARTED;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskBackendApplicationTests {

    @Autowired
    IssueService issueService;
    @Autowired
    TaskService taskService;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    IssueRepository issueRepository;

    /**
     * Testar att description i Issue sparas ner korrekt.
     * Jämför det som avses att sparas ner med det som sparades.
     */
    @Test
    public void addIssueTest() {

        String testDescription = "testDescription";
        Long taskNumber = 1000000007L;


        Issue tempIssue = issueService.saveIssue(new Issue(testDescription), taskNumber);
        TestCase.assertEquals(tempIssue.getDescription(), testDescription);

        Task task = taskService.getTask(taskNumber);
        TestCase.assertEquals(task.getStatus(), TaskStatus.UNSTARTED);

//        Resterande kod återställer databasen till förutvarande skick.
        Iterable<IssueDTO> allIssueDTOs = issueRepository.findAll();
        allIssueDTOs.forEach( k -> {
            if (k.getTaskDTO().getTaskNumber().equals(taskNumber)) {
                issueRepository.delete(k);
                task.setStatus(DONE);
                taskService.saveTask(task);
            }
        });
    }
}
