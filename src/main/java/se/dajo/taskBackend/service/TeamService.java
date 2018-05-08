package se.dajo.taskBackend.service;

import se.dajo.taskBackend.model.data.Team;
import se.dajo.taskBackend.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.dajo.taskBackend.repository.data.TeamDTO;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public Team saveTeam(Team team) {

        TeamDTO teamDTO = new TeamDTO(team.getTeamName(), team.getStatus());

        teamDTO = teamRepository.save(teamDTO);

        return new Team(teamDTO.getTeamName(), teamDTO.getStatus());
    }
}
