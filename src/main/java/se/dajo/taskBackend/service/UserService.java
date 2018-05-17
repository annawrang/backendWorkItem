package se.dajo.taskBackend.service;

import org.glassfish.jersey.internal.guava.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import se.dajo.taskBackend.enums.Status;
import se.dajo.taskBackend.model.data.Task;
import se.dajo.taskBackend.model.data.User;
import se.dajo.taskBackend.repository.TaskRepository;
import se.dajo.taskBackend.repository.TeamRepository;
import se.dajo.taskBackend.repository.UserRepository;
import se.dajo.taskBackend.repository.data.TaskDTO;
import se.dajo.taskBackend.repository.data.TeamDTO;
import se.dajo.taskBackend.repository.data.UserDTO;
import org.springframework.stereotype.Service;
import se.dajo.taskBackend.repository.parsers.TaskParser;
import se.dajo.taskBackend.repository.parsers.UserParser;
import se.dajo.taskBackend.resource.param.UserParam;
import se.dajo.taskBackend.service.exception.InvalidSpaceInTeamException;
import se.dajo.taskBackend.service.exception.InvalidUserNumberException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Service
public final class UserService {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final TaskRepository taskRepository;
    private final int maxUsersInTeam = 10;
    private AtomicLong userNumbers;

    @Autowired
    public UserService(UserRepository userRepository, TeamRepository teamRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.taskRepository = taskRepository;
    }

    public User saveUser(User user) {
        this.userNumbers = new AtomicLong(this.userRepository.getHighestUserNumber().orElse(1000000000L));
        user = user.setUserNumber(userNumbers.incrementAndGet());

        UserDTO userDTO = UserParser.toUserDTO(user);
        userDTO = userRepository.save(userDTO);
        return UserParser.toUser(userDTO);
    }

    public User getUser(Long userNumber) {
        UserDTO userDTO = userRepository.findUserDTOByUserNumber(userNumber);
        if (userDTO == null) {
            throw new InvalidUserNumberException();
        }
        return new User(userDTO.getFirstName(), userDTO.getSurName(), userDTO.getUserNumber(), userDTO.getStatus());
    }

    public List<User> getUserByFirstNAmeOrSurNameOrUserNumber(UserParam userParam) {
        List<User> user = checkUserParams(userParam);
        if (user.size() == 0) {
            throw new InvalidUserNumberException();
        }
        return user;
    }

    private List<User> checkUserParams(UserParam param) {
        if (param.firstName == null && param.surName == null && param.userNumber == null) {
            return UserParser.toUserList(Lists.newArrayList(userRepository.findAll()));
        } else if (param.firstName != null && param.surName == null && param.userNumber == null) {
            return UserParser.toUserList(userRepository.findByFirstName(param.firstName));
        } else if (param.firstName == null && param.surName != null && param.userNumber == null) {
            return UserParser.toUserList(userRepository.findBySurName(param.surName));
        } else if (param.firstName == null && param.surName == null && param.userNumber != null) {
            return UserParser.toUserList(userRepository.findByUserNumber(param.userNumber));
        } else if (param.firstName != null && param.surName != null && param.userNumber == null) {
            return UserParser.toUserList(userRepository.findByFirstNameAndSurName(param.firstName, param.surName));
        } else if (param.firstName != null && param.surName == null && param.userNumber != null) {
            return UserParser.toUserList(userRepository.findByFirstNameAndUserNumber(param.firstName, param.userNumber));
        } else if (param.firstName == null && param.surName != null && param.userNumber != null) {
            return UserParser.toUserList(userRepository.findBySurNameAndUserNumber(param.surName, param.userNumber));
        } else {
            return UserParser.toUserList(userRepository.findByFirstNameAndSurNameAndUserNumber(param.firstName, param.surName, param.userNumber));
        }
    }

    public User updateUser(String teamName, Long userNumber) {
        TeamDTO teamDTO = teamRepository.findTeamDTOByTeamName(teamName);
        if (checkForSpaceInTeam(teamDTO) == false) {
            throw new InvalidSpaceInTeamException();
        }
        UserDTO userDTO = userRepository.findUserDTOByUserNumber(userNumber);
        userDTO.setTeam(teamDTO);
        userRepository.save(userDTO);

        return new User(userDTO.getFirstName(), userDTO.getSurName(), userDTO.getUserNumber(), userDTO.getStatus());
    }

    public boolean checkForSpaceInTeam(TeamDTO teamDTO) {

        return userRepository.countUserDTOByTeam(teamDTO) < maxUsersInTeam;
    }

    public void updateUser(User user) {
        UserDTO oldUserDTO = userRepository.findByUserNumber(user.getUserNumber()).get(0);
        if (oldUserDTO == null) {
            throw new InvalidUserNumberException();
        }
        oldUserDTO = UserParser.updateUserDTO(oldUserDTO, user);
        updateUsersTasks(oldUserDTO);
        userRepository.save(oldUserDTO);
    }

    private void updateUsersTasks(UserDTO userDTO) {
        if (userDTO.getStatus().equals(Status.INACTIVE)) {
            taskRepository.setUsersTasksUnstarted(userDTO.getId());
        }
    }

    public List<Task> getUsersTasks(Long userNumber) {
        List<UserDTO> userDTOS = userRepository.findByUserNumber(userNumber);
        UserDTO userDTO = userDTOS.get(0);
        if (userDTO == null) {
            throw new InvalidUserNumberException();
        }
        List<TaskDTO> taskDTOS = taskRepository.getTaskDTOsInUserDTO(userDTO.getId());
        return TaskParser.toTaskList(taskDTOS);
    }
}