package main.java.se.dajo.taskBackend.repository;

import main.java.se.dajo.taskBackend.repository.data.TaskDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<TaskDTO, Long> {
}
