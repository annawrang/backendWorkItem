package se.dajo.taskBackend.model.data;

import se.dajo.taskBackend.enums.Status;

import java.util.concurrent.atomic.AtomicLong;

public class User {

    private String firstName;
    private String surName;
    private Long userNumber;
    private Status status;

    protected User(){}

    public User(String firstName, String surName, Status status) {
        this.firstName = firstName;
        this.surName = surName;
        this.status = status;
    }

    public User(String firstName, String surName, Long userNumber, Status status) {
        this.firstName = firstName;
        this.surName = surName;
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

    public User setUserNumber(Long userNumber) {
        this.userNumber = userNumber;
        return this;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public User setSurName(String surName) {
        this.surName = surName;
        return this;
    }

    public User setStatus(Status status) {
        this.status = status;
        return this;
    }
}
