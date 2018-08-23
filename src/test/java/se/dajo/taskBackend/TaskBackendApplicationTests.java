package se.dajo.taskBackend;


import junit.framework.TestCase;
import org.glassfish.jersey.uri.internal.JerseyUriBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.dajo.taskBackend.enums.Status;
import se.dajo.taskBackend.model.data.User;
import se.dajo.taskBackend.repository.TaskRepository;
import se.dajo.taskBackend.repository.TeamRepository;
import se.dajo.taskBackend.repository.UserRepository;
import se.dajo.taskBackend.repository.data.TeamDTO;
import se.dajo.taskBackend.repository.data.UserDTO;
import se.dajo.taskBackend.resource.TeamResource;
import se.dajo.taskBackend.resource.UserResource;
import se.dajo.taskBackend.service.UserService;
import se.dajo.taskBackend.service.exception.InvalidSpaceInTeamException;
import se.dajo.taskBackend.service.exception.InvalidUserNumberException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskBackendApplicationTests {

    public TaskBackendApplicationTests() {
    }

    @Autowired
    private TeamResource teamResource;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    TaskRepository taskRepository;


    @Test
    public void contextLoads() {
    }


    /* Testing that a member is added to a team correctly. Checks that the user is referencing to a
     * team in the database.
     * */
    @Test
    public void addMemberToATeam() {

        TeamDTO team = new TeamDTO("DevTeam1", Status.ACTIVE);
        UserDTO user = new UserDTO("Anna", "Wrang", 12377L, Status.ACTIVE);
        team = teamRepository.save(team);
        Long teamId = team.getId();
        user = userRepository.save(user);

        teamResource.addTeamUser(team.getTeamName(), user.getUserNumber());

        System.out.println(team.getTeamName());
        System.out.println(user.getUserNumber());
        System.out.println(teamId);

        user = userRepository.findUserDTOByUserNumber(user.getUserNumber());

        TestCase.assertEquals(teamId, user.getTeam().getId());

        userRepository.delete(user);
        teamRepository.delete(team);
    }


    /* Tests that a user cannot be added to a team which already has 10 members.
    *
    * */
    @Test(expected = InvalidSpaceInTeamException.class)
    public void addMemberToAFullTeam() {
        TeamDTO team = new TeamDTO("QATeam", Status.ACTIVE);
        UserDTO user1 = new UserDTO("Jozephine", "Grönqvist", 124L, Status.ACTIVE);
        UserDTO user2 = new UserDTO("Oscar", "Husmark", 125L, Status.ACTIVE);
        UserDTO user3 = new UserDTO("Anna", "Wrang", 126L, Status.ACTIVE);
        UserDTO user4 = new UserDTO("David", "Larsson", 127L, Status.ACTIVE);
        UserDTO user5 = new UserDTO("Chris", "Light", 128L, Status.ACTIVE);
        UserDTO user6 = new UserDTO("Bashar", "Mengana", 129L, Status.ACTIVE);
        UserDTO user7 = new UserDTO("Annie", "Jansson", 1231L, Status.ACTIVE);
        UserDTO user8 = new UserDTO("Fredrik", "Karlsson", 1232L, Status.ACTIVE);
        UserDTO user9 = new UserDTO("Johanna", "Jönsson", 1233L, Status.ACTIVE);
        UserDTO user10 = new UserDTO("Sofie", "Thorsen", 1234L, Status.ACTIVE);
        UserDTO user11 = new UserDTO("Per", "Freilich", 1235L, Status.ACTIVE);
        team = teamRepository.save(team);
        user1 = userRepository.save(user1);
        user2 = userRepository.save(user2);
        user3 = userRepository.save(user3);
        user4 = userRepository.save(user4);
        user5 = userRepository.save(user5);
        user6 = userRepository.save(user6);
        user7 = userRepository.save(user7);
        user8 = userRepository.save(user8);
        user9 = userRepository.save(user9);
        user10 = userRepository.save(user10);
        user11 = userRepository.save(user11);

        teamResource.addTeamUser(team.getTeamName(), user1.getUserNumber());
        teamResource.addTeamUser(team.getTeamName(), user2.getUserNumber());
        teamResource.addTeamUser(team.getTeamName(), user3.getUserNumber());
        teamResource.addTeamUser(team.getTeamName(), user4.getUserNumber());
        teamResource.addTeamUser(team.getTeamName(), user5.getUserNumber());
        teamResource.addTeamUser(team.getTeamName(), user6.getUserNumber());
        teamResource.addTeamUser(team.getTeamName(), user7.getUserNumber());
        teamResource.addTeamUser(team.getTeamName(), user8.getUserNumber());
        teamResource.addTeamUser(team.getTeamName(), user9.getUserNumber());
        teamResource.addTeamUser(team.getTeamName(), user10.getUserNumber());
        teamResource.addTeamUser(team.getTeamName(), user11.getUserNumber());

        TestCase.assertTrue(userService.thrownInvalidSpaceInTeamException == true);

        userRepository.delete(user1);
        userRepository.delete(user2);
        userRepository.delete(user3);
        userRepository.delete(user4);
        userRepository.delete(user5);
        userRepository.delete(user6);
        userRepository.delete(user7);
        userRepository.delete(user8);
        userRepository.delete(user9);
        userRepository.delete(user10);
        userRepository.delete(user11);

        teamRepository.delete(team);
    }

    @Test
    public void createUserTestHappyFlow() {
        User user = new User("Mark", "Hansen", null, Status.ACTIVE);
        User saved = userService.saveUser(user);
        UserDTO found = userRepository.findUserDTOByUserNumber(saved.getUserNumber());
        assertEquals(user.getFirstName(), found.getFirstName());
        assertEquals(user.getSurName(), found.getSurName());
        assertEquals(user.getStatus(), found.getStatus());
    }

    @Test(expected = InvalidUserNumberException.class)
    public void createUserTestSadFlow() {
        User user = new User("Mark", "Hansen", -1L, Status.ACTIVE);
        User saved = userService.saveUser(user);
        UserDTO found = userRepository.findUserDTOByUserNumber(saved.getUserNumber());
    }

    @Test
    public void updateUserTestHappyFlow() {
        User user = new User("Mark", "Hansen", null, Status.ACTIVE);
        User first = userService.saveUser(user);
        first.setStatus(Status.INACTIVE);
        User second = userService.saveUser(first);
        assertEquals(user.getFirstName(), second.getFirstName());
        assertEquals(user.getSurName(), second.getSurName());
        assertNotEquals(user.getStatus(), second.getStatus());
    }

    @Test
    public void createUserHappyFlow() {

        // Mock with mockito to avoid having to call database
        UriInfo uriInfo = Mockito.mock(UriInfo.class);
        when(uriInfo.getAbsolutePathBuilder()).thenReturn(new JerseyUriBuilder());

        // Create the "fake user" that we return from the mocked repository
        UserDTO user = new UserDTO("Mark", "Hansen", 1000000000L, Status.ACTIVE);

        UserRepository userRepo = Mockito.mock(UserRepository.class);
        when(userRepo.save(any())).thenReturn(user);

        userService = new UserService(userRepo, null, null);

        UserResource resource = new UserResource(userService, null);
        Response response = resource.createUser(uriInfo, new User("Mark", "Hansen", null, Status.ACTIVE));

        assertEquals(201, response.getStatus());
    }

}
