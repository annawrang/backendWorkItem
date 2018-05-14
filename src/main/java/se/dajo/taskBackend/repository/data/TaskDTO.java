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

    @ManyToOne
    private UserDTO user;

    protected TaskDTO(){}

    public TaskDTO(String description, TaskStatus status) {
        this.description = description;
        this.status = status;
    }

    public TaskDTO(Long id, String description, TaskStatus status) {
        this.id = id;
        this.description = description;
        this.status = status;
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

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public TaskDTO updateUserDTO(Task task){
        return new TaskDTO(this.id, task.getDescription(), task.getStatus());
    }
}
