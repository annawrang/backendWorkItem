package main.java.se.dajo.taskBackend.config;

import main.java.se.dajo.taskBackend.resource.IssueResource;
import main.java.se.dajo.taskBackend.resource.TaskResource;
import main.java.se.dajo.taskBackend.resource.TeamResource;
import main.java.se.dajo.taskBackend.resource.UserResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig{

    public JerseyConfig(){
        register(UserResource.class);
        register(IssueResource.class);
        register(TeamResource.class);
        register(TaskResource.class);
    }
}
