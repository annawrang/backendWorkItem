package se.dajo.taskBackend.repository.data;

import se.dajo.taskBackend.enums.Status;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public final class UserDTO {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String surName;
    private Long userNumber;
    private Status status;

    @ManyToOne
    private TeamDTO team;

    protected UserDTO() {
    }

    public UserDTO(Long id, String firstName, String surName, Long userNumber, Status status) {
        this.id = id;
        this.firstName = firstName;
        this.surName = surName;
        this.userNumber = userNumber;
        this.status = status;
    }

    public UserDTO(String firstName, String surName, Long userNumber, Status status) {
        this.firstName = firstName;
        this.surName = surName;
        this.userNumber = userNumber;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public Long getUserNumber() {
        return userNumber;
    }

    public Status getStatus() {
        return status;
    }

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
