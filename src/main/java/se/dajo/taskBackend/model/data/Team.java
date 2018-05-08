package se.dajo.taskBackend.model.data;

import se.dajo.taskBackend.enums.Status;

public class Team {

    //teams/dev/users

    private String teamName;
    private Status status;

    public Team(String teamName, Status status) {
        this.teamName = teamName;
        this.status = status;
    }

    public String getTeamName() {
        return teamName;
    }

    public Status getStatus() {
        return status;
    }

    // HÄR ÄR ETT FÖRSLAG (SOM ANDERS ANVÄNDE) ISTÄLLET FÖR EN VANLIG SETTER
    public Team setStatus(Status status) {
        return new Team(this.teamName, status);
    }

}
