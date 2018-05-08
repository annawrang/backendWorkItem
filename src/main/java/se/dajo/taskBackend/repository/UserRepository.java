package se.dajo.taskBackend.repository;

import se.dajo.taskBackend.model.data.User;
import se.dajo.taskBackend.repository.data.UserDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDTO, Long>{
}
