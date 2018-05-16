package se.dajo.taskBackend.repository.parsers;

import se.dajo.taskBackend.model.data.User;
import se.dajo.taskBackend.repository.data.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserParser {

    public static User toUser(UserDTO userDTO) {
        return new User(userDTO.getFirstName(), userDTO.getSurName(),
                userDTO.getUserNumber(), userDTO.getStatus());
    }

    public static List<User> toUserList(List<UserDTO> userDTOS) {
        List<User> modelUsers = new ArrayList<>();
        userDTOS.forEach(u -> {
            modelUsers.add(UserParser.toUser(u));
        });
        return modelUsers;
    }

    public static UserDTO updateUserDTO(UserDTO userDTO, User user) {
        return new UserDTO(userDTO.getId(), user.getFirstName(), user.getSurName(), user.getUserNumber(), user.getStatus());
    }

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user.getFirstName(), user.getSurName(), user.getUserNumber(), user.getStatus());
    }
}
