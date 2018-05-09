package se.dajo.taskBackend.service;

import se.dajo.taskBackend.model.data.Team;
import se.dajo.taskBackend.model.data.User;
import se.dajo.taskBackend.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.dajo.taskBackend.repository.UserRepository;
import se.dajo.taskBackend.repository.data.TeamDTO;
import se.dajo.taskBackend.repository.data.UserDTO;
import se.dajo.taskBackend.service.exception.InvalidUserNumberException;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;

    public Team saveTeam(Team team) {

        TeamDTO teamDTO = new TeamDTO(team.getTeamName(), team.getStatus());

        teamDTO = teamRepository.save(teamDTO);

        return new Team(teamDTO.getTeamName(), teamDTO.getStatus());
    }

    public Team getTeam(String teamName) {
        TeamDTO teamDTO = teamRepository.findTeamDTOByTeamName(teamName);
        return new Team(teamDTO.getTeamName(), teamDTO.getStatus());
    }

    public User updateUser(String teamName, Long userNumber) {
        UserDTO userDTO = userRepository.findUserDTOByUserNumber(userNumber);

        TeamDTO teamDTO = teamRepository.findTeamDTOByTeamName(teamName);

        userDTO.setTeam(teamDTO);
        userRepository.save(userDTO);

        return new User(userDTO.getFirstName(), userDTO.getSurName(), userDTO.getUserNumber(), userDTO.getStatus());
    }
}
