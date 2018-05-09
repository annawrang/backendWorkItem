package se.dajo.taskBackend.service;

import se.dajo.taskBackend.model.data.User;
import se.dajo.taskBackend.repository.UserRepository;
import se.dajo.taskBackend.repository.data.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.dajo.taskBackend.resource.param.UserParam;
import se.dajo.taskBackend.service.exception.InvalidUserIdException;
import se.dajo.taskBackend.service.exception.InvalidUserNumberException;
import se.dajo.taskBackend.service.exception.PropertyMissingException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        checkUserNumberLength(user);
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

    /*
    public List<User> getUserByFirstNAmeOrSurNameOrUserNumber(UserParam userParam) {
        return checkUserParams(userParam);
    } */

    public List<User> getUserByFirstNAmeOrSurNameOrUserNumber(String firstName, String surName, String userNumber){

        return checkUserParams(firstName, surName, userNumber);
    }

    private List<User> checkUserParams(String firstName, String surName, String userNumberIn) {
       /* Long userNumber = Long.parseLong(param.getUserNumber().toString());
        String firstName = param.getFirstName();
        String surName = param.getSurName(); */
        Long userNumber = Long.parseLong(userNumberIn);
        if (!firstName.equals("0")) {
            if (!surName.equals("0")) {
                if (userNumber != 0) {
                    System.out.println("1");
                    return userRepository.findByFirstNameAndSurNameAndUserNumber(firstName, surName, userNumber);
                } else {
                    System.out.println("2");
                    return userRepository.findByFirstNameAndSurName(firstName, surName);
                }
            } else {
                if (userNumber != 0) {
                    System.out.println("3");
                    return userRepository.findByFirstNameAndUserNumber(firstName, userNumber);
                } else{
                    System.out.println("hej");
                    List<User> userssss = userRepository.findByFirstName(firstName);
                    userssss.forEach(user -> System.out.println(user.getFirstName()));
                    return userRepository.findByFirstName(firstName);
                }
            }
        }
        else {
            if (!surName.equals("0")) {
                if (userNumber != 0) {
                    System.out.println("4");
                    return userRepository.findBySurNameAndUserNumber(surName, userNumber);
                } else{
                    System.out.println("5");
                    return userRepository.findBySurName(surName);
                }
            }
            else {
                if (userNumber != 0) {
                    System.out.println("6");
                    return userRepository.findByUserNumber(userNumber);
                }
                else {
                    throw new PropertyMissingException("You need to provide firstName and/or surName and/or userNumber");
                }
            }
        }
    }

    private void checkUserNumberLength(User user) {
        if(user.getUserNumber() < 10) {
            throw new InvalidUserNumberException("User number must be no more then 10 number long");
        }
    }
}