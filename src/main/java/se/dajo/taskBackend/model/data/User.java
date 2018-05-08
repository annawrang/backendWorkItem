package se.dajo.taskBackend.model.data;

import se.dajo.taskBackend.enums.Status;

public class User {

    private String firstName;
    private String surName;
    private Long userNumber;
    private Status status;

    protected User() {}
    public User(String firstName, String surName, Long userNumber) {
        this.firstName = firstName;
        this.surName = surName;
        this.userNumber = userNumber;
        this.status = Status.ACTIVE;
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

    // FÖRSLAG (SOM ANDERS ANVÄNDE) ISTÄLLET FÖR VANLIG SETTER
    public User setStatus(Status status) {
        this.status = status;
        return this;
    }
}
