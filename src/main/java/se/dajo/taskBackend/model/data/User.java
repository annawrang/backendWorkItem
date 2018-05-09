package se.dajo.taskBackend.model.data;

import se.dajo.taskBackend.enums.Status;

import java.util.concurrent.atomic.AtomicLong;

public class User {

    private String firstName;
    private String surName;
    private Long userNumber;
    private Status status;

    //private static final AtomicLong ids = new AtomicLong(1000000000);

    protected User(){}

//    public User(String firstName, String surName, Status status) {
//        this.firstName = firstName;
//        this.surName = surName;
//        this.userNumber = ids.getAndIncrement();
//        this.status = status;
//    }

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

}
