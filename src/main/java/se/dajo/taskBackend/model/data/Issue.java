package main.java.se.dajo.taskBackend.model.data;

public class Issue {

    private String description;
    private Task task;

    public Issue(String description, Task task) {
        this.description = description;
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public Task getTask() {
        return task;
    }
}
