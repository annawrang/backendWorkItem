package se.dajo.taskBackend.repository.data;

import se.dajo.taskBackend.enums.Status;
import se.dajo.taskBackend.model.data.Team;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TeamDTO {

    @Id
    @GeneratedValue
    private Long id;
    private String teamName;
    private Status status;

    protected TeamDTO(){}

    public TeamDTO(String teamName, Status status) {
        this.teamName = teamName;
        this.status = status;
    }

    public TeamDTO(Long id, String teamName, Status status) {
        this.id = id;
        this.teamName = teamName;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TeamDTO updateTeamDTO(Team team) {
        return new TeamDTO(this.id, team.getTeamName(), team.getStatus());
    }
}
