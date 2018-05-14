package se.dajo.taskBackend.repository;

import se.dajo.taskBackend.repository.data.TaskDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<TaskDTO, Long> {

    TaskDTO findTaskDTOByTaskNumber(Long taskNumber);
}
