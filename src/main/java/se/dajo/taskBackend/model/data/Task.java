package se.dajo.taskBackend.model.data;

import se.dajo.taskBackend.enums.TaskStatus;

public class Task {

    private String description;
    private TaskStatus status;

    public Task(String description, TaskStatus taskStatus) {
        this.description = description;
        this.status = taskStatus;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    // ETT FÖRSLAG ISTÄLLET FÖR "VANLIG" SETTER (SOM ANDERS ANVÄNDE - GOOD PRACTICE ATT SKICKA
    // TILLBAKA ETT NYTT OBJEKT ISTÄLLET FÖR ATT MODIFIERA DET GAMLA)
//    public Task setTaskStatus(TaskStatus taskStatus) {
//        return new Task(this.description, taskStatus);
//    }
}
