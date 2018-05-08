package se.dajo.taskBackend.repository.data;

import se.dajo.taskBackend.model.data.User;

import javax.persistence.*;

@Entity
public class TaskDTO {

    @Id
    @GeneratedValue
    private Long id;
    private String description;

    @ManyToOne
    private UserDTO user;

    protected TaskDTO(){}

    public TaskDTO(String description) {
        this.description = description;
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

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
