package se.dajo.taskBackend.service;

import se.dajo.taskBackend.model.data.User;
import se.dajo.taskBackend.repository.UserRepository;
import se.dajo.taskBackend.repository.data.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.dajo.taskBackend.service.exception.InvalidUserIdException;
import se.dajo.taskBackend.service.exception.InvalidUserNumberException;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        checker(user);
        // Creates a UserDTO from the User-object
        UserDTO userDTO = new UserDTO(user.getFirstName(),
                user.getSurName(),
                user.getUserNumber(),
                user.getStatus());

        userDTO = userRepository.save(userDTO);

        // Creates a User from the UserDTO-object
        return new User(userDTO.getFirstName(),
                userDTO.getSurName(),
                userDTO.getUserNumber(),
                userDTO.getStatus());
    }

    public User getUser(Long id) {
        UserDTO userDTO = userRepository.findById(id).orElseThrow(() -> new InvalidUserIdException("User not found"));

        User user = new User(userDTO.getFirstName(), userDTO.getSurName(), userDTO.getUserNumber(), userDTO.getStatus());

        return user;
    }

    private void checker(User user) {
        if(user.getUserNumber() < 10) {
            throw new InvalidUserNumberException("User number must be no more then 10 number long");
        }
    }
}