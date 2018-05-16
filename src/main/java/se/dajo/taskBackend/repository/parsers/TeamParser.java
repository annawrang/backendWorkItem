package se.dajo.taskBackend.repository.parsers;

import se.dajo.taskBackend.model.data.Team;
import se.dajo.taskBackend.repository.data.TeamDTO;

import java.util.ArrayList;
import java.util.List;

public final class TeamParser {

    public static Team parseTeamDTOToTeam(TeamDTO teamDTO) {
        return new Team(teamDTO.getTeamName(), teamDTO.getStatus());
    }

    public static List<Team> parseTeamDTOToTeamList(List<TeamDTO> teamDTOS) {
        List<Team> teams = new ArrayList<>();
        teamDTOS.forEach(teamDTO -> teams.add(parseTeamDTOToTeam(teamDTO)));
        return teams;
    }

    public static TeamDTO prepareForUpdateTeamDTO(Team team) {
        return new TeamDTO(team.getTeamName(), team.getStatus());
    }

}

