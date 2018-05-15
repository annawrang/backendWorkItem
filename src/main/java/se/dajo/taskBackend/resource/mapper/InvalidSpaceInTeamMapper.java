package se.dajo.taskBackend.resource.mapper;

import se.dajo.taskBackend.service.exception.InvalidSpaceInTeamException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static java.util.Collections.singletonMap;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
public final class InvalidSpaceInTeamMapper implements ExceptionMapper<InvalidSpaceInTeamException> {
    @Override
    public Response toResponse(InvalidSpaceInTeamException exception) {
        return Response.status(BAD_REQUEST).entity(singletonMap("Crowd control", exception.getMessage())).build();
    }

}
