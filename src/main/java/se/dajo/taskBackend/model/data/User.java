package se.dajo.taskBackend.model.data;

import se.dajo.taskBackend.enums.Status;

public class User {

    private String firstName;
    private String surName;
    private Long userNumber;
    private Status status;

    public User(String firstName, String surname, Long userNumber, Status status) {
        this.firstName = firstName;
        this.surName = surname;
        this.userNumber = userNumber;
        this.status = status;
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
        return new User(this.firstName, this.surName, this.userNumber, status);
    }
}
