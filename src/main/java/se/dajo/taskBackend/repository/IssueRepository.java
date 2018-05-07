package main.java.se.dajo.taskBackend.repository;

import main.java.se.dajo.taskBackend.repository.data.IssueDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends CrudRepository<IssueDTO, Long> {
}
