package se.dajo.taskBackend.service;

import se.dajo.taskBackend.model.data.User;
import se.dajo.taskBackend.repository.data.UserDTO;

import java.util.ArrayList;
import java.util.List;

public final class Parser {

    public static User createUser(UserDTO userDTO){
        return new User(userDTO.getFirstName(), userDTO.getSurName(),
                userDTO.getUserNumber(), userDTO.getStatus());
    }

    public static List<User> createUserList(List<UserDTO> userDTOS){
        List<User> modelUsers = new ArrayList<>();
        userDTOS.forEach(u -> {
            modelUsers.add(Parser.createUser(u));
        });
        return modelUsers;
    }
}