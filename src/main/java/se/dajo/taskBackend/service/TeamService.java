package se.dajo.taskBackend.service;

import se.dajo.taskBackend.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;
}
