package se.dajo.taskBackend.repository;

import org.springframework.data.jpa.repository.Query;
import se.dajo.taskBackend.repository.data.IssueDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.dajo.taskBackend.repository.data.TaskDTO;

import java.util.Optional;

@Repository
public interface IssueRepository extends CrudRepository<IssueDTO, Long> {

    //@Query("SELECT MAX(taskNumber) FROM TaskDTO")
    //Optional<Long> getHighestTaskNumber();
}
