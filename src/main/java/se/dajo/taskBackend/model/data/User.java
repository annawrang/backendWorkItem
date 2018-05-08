package se.dajo.taskBackend.model.data;

import se.dajo.taskBackend.model.Status;

public class User {

    private String firstName;
    private String surname;
    private Long userNumber;
    private Status status;

    public User(String firstName, String surname, Long userNumber, Status status) {
        this.firstName = firstName;
        this.surname = surname;
        this.userNumber = userNumber;
        this.status = status;
    }

    protected User(){}

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public Long getUserNumber() {
        return userNumber;
    }

    public Status getStatus() {
        return status;
    }

    // FÖRSLAG (SOM ANDERS ANVÄNDE) ISTÄLLET FÖR VANLIG SETTER
    public User setStatus(Status status) {
        return new User(this.firstName, this.surname, this.userNumber, status);
    }
}
