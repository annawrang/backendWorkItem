package se.dajo.taskBackend.service;

import se.dajo.taskBackend.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

}
