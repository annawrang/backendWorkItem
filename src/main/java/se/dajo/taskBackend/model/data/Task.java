package se.dajo.taskBackend.model.data;

import se.dajo.taskBackend.enums.TaskStatus;

public class Task {

    private Long taskNumber;
    private String description;
    private TaskStatus taskStatus;

    public Task(Long taskNumber, String description, TaskStatus taskStatus) {
        this.taskNumber = taskNumber;
        this.description = description;
        this.taskStatus = taskStatus;
    }

    public Long getTaskNumber() {
        return taskNumber;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    // ETT FÖRSLAG ISTÄLLET FÖR "VANLIG" SETTER (SOM ANDERS ANVÄNDE - GOOD PRACTICE ATT SKICKA
    // TILLBAKA ETT NYTT OBJEKT ISTÄLLET FÖR ATT MODIFIERA DET GAMLA)
    public Task setTaskStatus(TaskStatus taskStatus) {
        return new Task(this.taskNumber, this.description, taskStatus);
    }
}
