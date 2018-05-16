package se.dajo.taskBackend.repository.data;

import se.dajo.taskBackend.enums.TaskStatus;
import se.dajo.taskBackend.model.data.Task;

import javax.persistence.*;

@Entity
public class TaskDTO {

    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private TaskStatus status;
    private Long taskNumber;

    @ManyToOne
    private UserDTO user;

    protected TaskDTO() {
    }

    public TaskDTO(String description, TaskStatus status, Long taskNumber) {
        this.description = description;
        this.status = status;
        this.taskNumber = taskNumber;
    }

    public TaskDTO(Long id, String description, TaskStatus status, Long taskNumber) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.taskNumber = taskNumber;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public UserDTO getUser() {
        return user;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Long getTaskNumber() {
        return taskNumber;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    // FUNKAR INTE ÄN
    //    public TaskDTO setUser(UserDTO user) {
//        return new TaskDTO(this.getId(), this.getDescription(),
//                this.getStatus(), this.getTaskNumber(), user);
//    }
}

