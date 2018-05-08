package se.dajo.taskBackend.repository;

import se.dajo.taskBackend.model.data.User;
import se.dajo.taskBackend.repository.data.UserDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserDTO, Long>{

    Optional<User> findByFirstNameAndSurNameAndUserNumber();

    Optional<User> findByFirstNameAndSurName();

    Optional<User> findByFirstNameAndUserNumber();

    Optional<User> findByFirstName();

    Optional<User> findBySurName();

    Optional<User> findBySurNameAndUserNumber();

    Optional<User> findByUserNumber();
}
