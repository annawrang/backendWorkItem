package main.java.se.dajo.taskBackend.repository;

import main.java.se.dajo.taskBackend.repository.data.TeamDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<TeamDTO, Long> {
}
