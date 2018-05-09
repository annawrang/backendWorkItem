package se.dajo.taskBackend.repository;

import se.dajo.taskBackend.model.data.User;
import se.dajo.taskBackend.repository.data.UserDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserDTO, Long>{

    List<User> findByFirstNameAndSurNameAndUserNumber(String firstName, String surName, Long userNumber);

    List<User> findByFirstNameAndSurName(String firstName, String surName);

    List<User> findByFirstNameAndUserNumber(String firstName, Long userNumber);

    List<User> findByFirstName(String firstName);

    List<User> findBySurName(String surName);

    List<User> findBySurNameAndUserNumber(String surName, Long userNumber);

    List<User> findByUserNumber(Long userNumber);
}
