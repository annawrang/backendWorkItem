package se.dajo.taskBackend.repository.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TeamDTO {

    @Id
    @GeneratedValue
    private Long id;
    private String teamName;

    protected TeamDTO(){}

    public TeamDTO(String teamName) {
        this.teamName = teamName;
    }

    public Long getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }
}
