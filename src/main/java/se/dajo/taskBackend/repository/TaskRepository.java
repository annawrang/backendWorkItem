package se.dajo.taskBackend.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import se.dajo.taskBackend.repository.data.TaskDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<TaskDTO, Long> {

    @Query("SELECT MAX(taskNumber) FROM TaskDTO")
    Optional<Long> getHighestTaskNumber();

    TaskDTO findByTaskNumber(Long taskNumber);

    @Transactional
    @Modifying
    @Query("update TaskDTO t set t.status = 0 where t.user.id = ?1")
    void setUsersTasksUnstarted(Long id);


}
