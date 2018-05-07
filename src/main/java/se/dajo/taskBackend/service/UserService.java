package main.java.se.dajo.taskBackend.service;

import main.java.se.dajo.taskBackend.model.data.User;
import main.java.se.dajo.taskBackend.repository.UserRepository;
import main.java.se.dajo.taskBackend.repository.data.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {

        // Creates a UserDTO from the User-object
        UserDTO userDTO = new UserDTO(user.getFirstName(),
                user.getSurname(),
                user.getUserNumber(),
                user.getStatus());

        userDTO = userRepository.save(userDTO);

        // Creates a User from the UserDTO-object
        return new User(userDTO.getFirstName(),
                userDTO.getSurName(),
                userDTO.getUserNumber(),
                userDTO.getStatus());
    }
}
