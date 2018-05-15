package se.dajo.taskBackend.repository;

import org.springframework.data.jpa.repository.Query;
import se.dajo.taskBackend.model.data.Team;
import se.dajo.taskBackend.repository.data.TeamDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.dajo.taskBackend.repository.data.UserDTO;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends CrudRepository<TeamDTO, Long> {

    TeamDTO findTeamDTOByTeamName(String teamName);

    @Query("select u from UserDTO u where u.team.id = ?1")
    List<UserDTO> getUserDTOSInTeamDTO(Long teamId);

}
