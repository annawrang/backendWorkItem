package se.dajo.taskBackend.service;

import org.glassfish.jersey.internal.guava.Lists;
import se.dajo.taskBackend.enums.Status;
import se.dajo.taskBackend.model.data.Task;
import se.dajo.taskBackend.model.data.User;
import se.dajo.taskBackend.repository.TaskRepository;
import se.dajo.taskBackend.repository.TeamRepository;
import se.dajo.taskBackend.repository.UserRepository;
import se.dajo.taskBackend.repository.data.TaskDTO;
import se.dajo.taskBackend.repository.data.TeamDTO;
import se.dajo.taskBackend.repository.data.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.dajo.taskBackend.repository.parsers.TaskParser;
import se.dajo.taskBackend.repository.parsers.UserParser;
import se.dajo.taskBackend.resource.param.UserParam;
import se.dajo.taskBackend.service.exception.InvalidSpaceInTeamException;
import se.dajo.taskBackend.service.exception.InvalidUserNumberException;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

    private final int maxUsersInTeam = 10;


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TaskRepository taskRepository;
    private AtomicLong userNumbers;

    public User saveUser(User user) {
        this.userNumbers = new AtomicLong(this.userRepository.getHighestUserNumber().orElse(1000000000L));
        user = user.setUserNumber(userNumbers.incrementAndGet());

        UserDTO userDTO = UserParser.parseUserToUserDTO(user);
        userDTO = userRepository.save(userDTO);
        return UserParser.parseUserDTOToUser(userDTO);
    }

    public User getUser(Long userNumber) {
        UserDTO userDTO = userRepository.findUserDTOByUserNumber(userNumber);
        if(userDTO == null){
            throw new InvalidUserNumberException("User not found");
        }
        return new User(userDTO.getFirstName(), userDTO.getSurName(), userDTO.getUserNumber(), userDTO.getStatus());
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
                    return UserParser.parseUserDTOListToUserList(userRepository.findByFirstNameAndSurNameAndUserNumber(firstName, surName, userNumber));
                } else {
                    return UserParser.parseUserDTOListToUserList(userRepository.findByFirstNameAndSurName(firstName, surName));
                }
            } else {
                if (userNumber != 0) {
                    return UserParser.parseUserDTOListToUserList(userRepository.findByFirstNameAndUserNumber(firstName, userNumber));
                } else {
                    return UserParser.parseUserDTOListToUserList(userRepository.findByFirstName(firstName));
                }
            }
        } else {
            if (!surName.equals("0")) {
                if (userNumber != 0) {
                    return UserParser.parseUserDTOListToUserList(userRepository.findBySurNameAndUserNumber(surName, userNumber));
                } else {
                    return UserParser.parseUserDTOListToUserList(userRepository.findBySurName(surName));
                }
            } else {
                if (userNumber != 0) {
                    return UserParser.parseUserDTOListToUserList(userRepository.findByUserNumber(userNumber));
                }
            }
        }
        return UserParser.parseUserDTOListToUserList(Lists.newArrayList(userRepository.findAll()));
    }

    public User updateUser(String teamName, Long userNumber) {
        TeamDTO teamDTO = teamRepository.findTeamDTOByTeamName(teamName);

        if (checkForSpaceInTeam(teamDTO) == false)
            throw new InvalidSpaceInTeamException("No space in team for user");

        UserDTO userDTO = userRepository.findUserDTOByUserNumber(userNumber);
        userDTO.setTeam(teamDTO);
        userRepository.save(userDTO);

        return new User(userDTO.getFirstName(), userDTO.getSurName(), userDTO.getUserNumber(), userDTO.getStatus());
    }

    public boolean checkForSpaceInTeam(TeamDTO teamDTO) {

        return userRepository.countUserDTOByTeam(teamDTO) < maxUsersInTeam;
    }

    public void updateUser(User user){
        UserDTO oldUserDTO = userRepository.findByUserNumber(user.getUserNumber()).get(0);
        if(oldUserDTO == null){
            throw new InvalidUserNumberException("No user found");
        }
        oldUserDTO = UserParser.prepareForUpdateUserDTO(oldUserDTO, user);
        updateUsersTasks(oldUserDTO);
        userRepository.save(oldUserDTO);
    }

    private void updateUsersTasks(UserDTO userDTO){
        if(userDTO.getStatus().equals(Status.INACTIVE)){
            taskRepository.setUsersTasksUnstarted(userDTO.getId());
        }
    }

    public List<Task> getUsersTasks(Long userNumber) {
        List<UserDTO> userDTOS = userRepository.findByUserNumber(userNumber);
        UserDTO userDTO = userDTOS.get(0);
        if(userDTO == null){
            throw new InvalidUserNumberException("No user found");
        }
        List<TaskDTO> taskDTOS = taskRepository.getTaskDTOsInUserDTO(userDTO.getId());
        return TaskParser.parseTaskDTOListToTaskList(taskDTOS);
    }


}