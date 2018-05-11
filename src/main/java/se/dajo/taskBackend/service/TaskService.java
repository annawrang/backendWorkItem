package se.dajo.taskBackend.service;

import se.dajo.taskBackend.model.data.Task;
import se.dajo.taskBackend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task saveTask() {
        return null;
    }

    pu
}
