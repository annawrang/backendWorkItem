package se.dajo.taskBackend.resource.mapper;

import se.dajo.taskBackend.service.exception.InvalidUserNumberException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static java.util.Collections.singletonMap;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
public final class InvalidUserNumberMapper implements ExceptionMapper<InvalidUserNumberException> {
    @Override
    public Response toResponse(InvalidUserNumberException exception) {
        return Response.status(BAD_REQUEST).entity(singletonMap("Invalid userNumber input", exception.getMessage())).build();
    }
}
