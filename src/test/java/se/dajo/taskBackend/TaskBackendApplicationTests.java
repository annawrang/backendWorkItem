package se.dajo.taskBackend;


import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.dajo.taskBackend.enums.TaskStatus;
import se.dajo.taskBackend.model.data.Task;
import se.dajo.taskBackend.repository.TaskRepository;
import se.dajo.taskBackend.resource.TaskResource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskBackendApplicationTests {

    @Autowired
    TaskResource taskResource;
    String description = "Eat";
    TaskStatus status = TaskStatus.UNSTARTED;
    Long taskNumber = 123555L;

	@Test
	public void createTaskTest() {
	    Task task = taskResource.createTask(new Task(description, status, taskNumber));

	}
}