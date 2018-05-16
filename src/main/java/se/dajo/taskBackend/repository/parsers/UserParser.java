package se.dajo.taskBackend.repository.parsers;

import se.dajo.taskBackend.model.data.User;
import se.dajo.taskBackend.repository.data.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserParser {

    public static User parseUserDTOToUser(UserDTO userDTO) {
        return new User(userDTO.getFirstName(), userDTO.getSurName(),
                userDTO.getUserNumber(), userDTO.getStatus());
    }

    public static List<User> parseUserDTOListToUserList(List<UserDTO> userDTOS) {
        List<User> modelUsers = new ArrayList<>();
        userDTOS.forEach(u -> {
            modelUsers.add(UserParser.parseUserDTOToUser(u));
        });
        return modelUsers;
    }

    public static UserDTO prepareForUpdateUserDTO(UserDTO userDTO, User user) {
        return new UserDTO(userDTO.getId(), user.getFirstName(), user.getSurName(), user.getUserNumber(), user.getStatus());
    }

    public static UserDTO parseUserToUserDTO(User user) {
        return new UserDTO(user.getFirstName(), user.getSurName(), user.getUserNumber(), user.getStatus());
    }
}
