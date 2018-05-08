package se.dajo.taskBackend.repository;

import se.dajo.taskBackend.repository.data.TeamDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<TeamDTO, Long> {
}
