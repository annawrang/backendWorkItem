package se.dajo.taskBackend.model.data;

import se.dajo.taskBackend.enums.TaskStatus;

public final class Task {

    private String description;
    private TaskStatus status;
    private Long taskNumber;

    public Task(String description, TaskStatus status, Long taskNumber) {
        this.description = description;
        this.status = status;
        this.taskNumber = taskNumber;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Long getTaskNumber() {
        return taskNumber;
    }

    public Task setTaskNumber(Long taskNumber) {
        this.taskNumber = taskNumber;
        return this;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
//
//    @Override
//    public boolean equals(Task task){
//        if(task instanceof Task){
//            return (task.taskNumber == taskNumber);
//        }
//        return false;
//    }
//
//    @Override
//    public int hashCode(){
//        return Integer.parseInt(this.taskNumber);
//    }
}
