package se.dajo.taskBackend.repository.data;

import javax.persistence.*;

@Entity
public class IssueDTO {

    @Id
    @GeneratedValue
    private Long id;
    private String description;

    @ManyToOne
    private TaskDTO taskDTO;

    protected IssueDTO(){}

    public IssueDTO(String description, TaskDTO taskDTO) {
        this.description = description;
        this.taskDTO = taskDTO;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public TaskDTO getTaskDTO() {
        return taskDTO;
    }

}

