package se.dajo.taskBackend.service;

import org.glassfish.jersey.internal.guava.Lists;
import se.dajo.taskBackend.enums.Status;
import se.dajo.taskBackend.model.data.User;
import se.dajo.taskBackend.repository.UserRepository;
import se.dajo.taskBackend.repository.data.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.dajo.taskBackend.resource.param.UserParam;
import se.dajo.taskBackend.service.exception.InvalidUserNumberException;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private AtomicLong userNumbers;

    public User saveUser(User user) {

        this.userNumbers = new AtomicLong(this.userRepository.getHighestUserNumber().orElse(1000000000L));
        user = user.setUserNumber(userNumbers.incrementAndGet());

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

    public User getUser(Long userNumber) {

        UserDTO userDTO = userRepository.findUserDTOByUserNumber(userNumber);

        User user = new User(userDTO.getFirstName(), userDTO.getSurName(), userDTO.getUserNumber(), userDTO.getStatus());

        return user;
    }

    public List<User> getUserByFirstNAmeOrSurNameOrUserNumber(UserParam userParam) {
        return checkUserParams(userParam);
    }

    private List<User> checkUserParams(UserParam param) {
        Long userNumber = Long.parseLong(param.getUserNumber().toString());
        String firstName = param.getFirstName();
        String surName = param.getSurName();

        if (!firstName.equals("0")) {
            if (!surName.equals("0")) {
                if (userNumber != 0) {
                    return Parser.createUserList(userRepository.findByFirstNameAndSurNameAndUserNumber(firstName, surName, userNumber));
                } else {
                    return Parser.createUserList(userRepository.findByFirstNameAndSurName(firstName, surName));
                }
            } else {
                if (userNumber != 0) {
                    return Parser.createUserList(userRepository.findByFirstNameAndUserNumber(firstName, userNumber));
                } else {
                    return Parser.createUserList(userRepository.findByFirstName(firstName));
                }
            }
        } else {
            if (!surName.equals("0")) {
                if (userNumber != 0) {
                    return Parser.createUserList(userRepository.findBySurNameAndUserNumber(surName, userNumber));
                } else {
                    return Parser.createUserList(userRepository.findBySurName(surName));
                }
            } else {
                if (userNumber != 0) {
                    return Parser.createUserList(userRepository.findByUserNumber(userNumber));
                }
            }
        }
        return Parser.createUserList(Lists.newArrayList(userRepository.findAll()));
    }

    public User deactivateUser(User user) {
        UserDTO userDTO = userRepository.findUserDTOByUserNumber((user.getUserNumber()));
        userDTO.setStatus(Status.INACTIVE);
        userRepository.save(userDTO);
        return user;
    }

    public void updateUser(User user){
        UserDTO oldUserDTO = userRepository.findByUserNumber(user.getUserNumber()).get(0);
        oldUserDTO = oldUserDTO.updateUserDTO(user);
        oldUserDTO = userRepository.save(oldUserDTO);
    }
}