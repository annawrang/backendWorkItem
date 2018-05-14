package se.dajo.taskBackend.resource.mapper;

import se.dajo.taskBackend.service.exception.InvalidTeamNameException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static java.util.Collections.singletonMap;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
public final class InvalidTeamNameMapper implements ExceptionMapper<InvalidTeamNameException> {
    @Override
    public Response toResponse(InvalidTeamNameException exception) {
        return Response.status(BAD_REQUEST).entity(singletonMap("Invalid teamname input", exception.getMessage())).build();
    }
}