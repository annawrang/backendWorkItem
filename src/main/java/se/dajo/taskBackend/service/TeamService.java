package se.dajo.taskBackend.service;

import org.glassfish.jersey.internal.guava.Lists;
import se.dajo.taskBackend.model.data.Task;
import se.dajo.taskBackend.model.data.Team;
import se.dajo.taskBackend.model.data.User;
import se.dajo.taskBackend.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.dajo.taskBackend.repository.data.TaskDTO;
import se.dajo.taskBackend.repository.data.TeamDTO;
import se.dajo.taskBackend.repository.data.UserDTO;
import se.dajo.taskBackend.repository.parsers.TaskParser;
import se.dajo.taskBackend.repository.parsers.TeamParser;
import se.dajo.taskBackend.repository.parsers.UserParser;
import se.dajo.taskBackend.service.exception.InvalidTeamNameException;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    public Team saveTeam(Team team) {
        TeamDTO teamDTO = new TeamDTO(team.getTeamName(), team.getStatus());
        teamDTO = teamRepository.save(teamDTO);
        return new Team(teamDTO.getTeamName(), teamDTO.getStatus());
    }

    public Team getTeam(String teamName) {
        TeamDTO teamDTO = teamRepository.findTeamDTOByTeamName(teamName);
        if(teamDTO == null){
            throw new InvalidTeamNameException();
        }
        return new Team(teamDTO.getTeamName(), teamDTO.getStatus());
    }

    public List<Team> getAllTeams() {
        List<TeamDTO> teamDTOS = Lists.newArrayList(teamRepository.findAll());
        return TeamParser.toTeamList(teamDTOS);
    }

    public void updateTeam(String teamName, Team team) {
        TeamDTO oldTeamDTO = teamRepository.findTeamDTOByTeamName(teamName);
        if(oldTeamDTO == null){
            throw new InvalidTeamNameException();
        }
        oldTeamDTO = TeamParser.updateTeamDTO(oldTeamDTO, team);
        teamRepository.save(oldTeamDTO);
    }

    public List<User> getUsersInTeam(String teamName) {
        TeamDTO teamDTO = teamRepository.findTeamDTOByTeamName(teamName);
        if(teamDTO == null){
            throw new InvalidTeamNameException();
        }
        List<UserDTO> userDTOS = teamRepository.getUserDTOSInTeamDTO(teamDTO.getId());
        return UserParser.toUserList(userDTOS);
    }

    public List<Task> getTasksInTeam(String teamName) {
        TeamDTO teamDTO = teamRepository.findTeamDTOByTeamName(teamName);
        if(teamDTO == null){
            throw new InvalidTeamNameException();
        }
        List<TaskDTO> taskDTOS = teamRepository.getTaskDTOSInTeamDTO(teamDTO.getId());
        return TaskParser.toTaskList(taskDTOS);
    }
}
